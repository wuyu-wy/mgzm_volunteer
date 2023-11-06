package com.blbd.volunteer.webSocket;

import com.alibaba.fastjson.JSON;
import com.blbd.volunteer.dao.entity.ChatMsgEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// @ServerEndpoint注解类似于Controller层的 @RequestMapping注解
// 此处websocket服务地址例如：http://127.0.0.1:8080/websocket/1,地址中1为userId，使用@PathParam("userId")提取
@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocketServer {
    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String userId="";


    /**
     * 连接建立成功调用的方法
     * */
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        this.session = session;
        this.userId=userId;
        //判断用户集合中是否存在当前用户
        if(webSocketMap.containsKey(userId)){
            //有则先删除已有用户，再添加
            webSocketMap.remove(userId);
            //加入set中
            webSocketMap.put(userId,this);
        }else{
            //加入set中
            webSocketMap.put(userId,this);
            //在线数加1
            addOnlineCount();
        }
        log.info("用户连接: "+userId+",当前在线人数为: " + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("用户: "+userId+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭执行
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            //将当前用户在集合中删除
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户【"+userId+"】退出: 当前在线人数为: " + getOnlineCount());
    }

    /**
     * 收到客户端消息时执行
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("当前用户: "+userId+"，报文: "+message);
        if(StringUtils.isNotBlank(message)){
            try {
                //传送给对应userId用户的websocket, 如果userId不为空 并且 webSocketMap种包含userId 才会发送消息
                if(StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)){
                    webSocketMap.get(userId).sendMessage("心跳响应");
                    //处理用户发来的消息
                    handleTextMessage(session, new TextMessage(message));
                }else{
                    //否则不在这个服务器上
                    log.error("请求的userId："+userId+" 不在该服务器上");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 链接异常时执行
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }

    /**
     * 处理信息
     */
    public void handleTextMessage(Session session, TextMessage message) throws Exception {
        log.warn("=========== Received message: {}", message.getPayload());

        //回应客户端心跳ping
        if ("ping".equals(message.getPayload())) {
//            webSocketMap.get(userId).sendMessage("pong");
            return;
        }

        //承接客户端message
        ChatMsgEntity chatMsgEntity = JSON.parseObject(message.getPayload(), ChatMsgEntity.class);
        //根据消息类型分别处理
        if (chatMsgEntity.getMsgType() == SocketConstants.MsgType.HEART_BEAT) {
            // 心跳消息

        } else if (chatMsgEntity.getMsgType() == SocketConstants.MsgType.LOGIN) {
            log.info("登录消息，用户:" + chatMsgEntity.getSenderId() + "上线");

        } else if (chatMsgEntity.getMsgType() == SocketConstants.MsgType.TEXT_MESSAGE) {
            if (StringUtils.isBlank(chatMsgEntity.getSenderId())) {
                throw new Exception("用户ID不能为空");
            }
            sendOneMessage( chatMsgEntity.getSenderId(), (String) chatMsgEntity.getMsgBody(), chatMsgEntity.getReceiverId());

        } else if (chatMsgEntity.getMsgType() == SocketConstants.MsgType.IMAGE) {
            if (StringUtils.isBlank(chatMsgEntity.getSenderId())) {
                throw new Exception("用户ID不能为空");
            }


        } else if (chatMsgEntity.getMsgType() == SocketConstants.MsgType.FILE) {
            if (StringUtils.isBlank(chatMsgEntity.getSenderId())) {
                throw new Exception("用户ID不能为空");
            }

        } else {
            log.error("消息类型错误");
        }
    }


    /**
     * 实现服务器主动推送
     * getAsyncRemote：是非阻塞式的
     * getBasicRemote：是阻塞式的
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);//同步消息
//        this.session.getAsyncRemote().sendText(message);//异步消息
    }
    // --------------------以下三种主动发送消息方法---------------------
    // --------------------通过注入WebSocketServer类使用---------------------
    /**
     * 单体消息
     * */
    public void sendOneMessage(String senderId, String message, String receiverId) throws IOException {
        log.info(senderId + "发送单体消息到: "+receiverId+"，报文:"+ message );
        if(StringUtils.isNotBlank(receiverId)&&webSocketMap.containsKey(receiverId)){
            webSocketMap.get(receiverId).sendMessage(message);
        }else{
            log.error("用户: "+receiverId+",不在线！");
        }
    }
    /**
     * 多人消息
     * */
    public void sendMoreMessage(String message, String[] userIds) throws IOException {
        log.info("多人消息报文: "+message);
        for (String id : userIds) {
            if(webSocketMap.containsKey(id)){
                webSocketMap.get(id).sendMessage(message);
            }else{
                log.error("用户: "+id+",不在线！");
            }
        }
    }
    /**
     * 广播消息
     * */
    public void sendAllMessage(String message) throws IOException {
        log.info("广播消息报文: "+message);
        for (Map.Entry<String,WebSocketServer> entry : webSocketMap.entrySet()){
            entry.getValue().sendMessage(message);
        }
    }

    /**
     * 获取当前在线人数
     * @return
     */
    public synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 在线人数加1
     */
    public synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    /**
     * 在线人数减1
     */
    public synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}

