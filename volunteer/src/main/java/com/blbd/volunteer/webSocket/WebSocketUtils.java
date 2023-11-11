package com.blbd.volunteer.webSocket;

import com.alibaba.fastjson.JSON;
import com.blbd.volunteer.dao.entity.ChatFriendListEntity;
import com.blbd.volunteer.dao.entity.ChatMsgEntity;
import com.blbd.volunteer.service.ChatFriendListService;
import com.blbd.volunteer.service.ChatMsgService;
import com.blbd.volunteer.utils.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class WebSocketUtils {
    /**
     * 定义静态，然后给类的静态service注入
     */
    private static ChatMsgService chatMsgService;
    private static ChatFriendListService chatFriendListService;
    @Autowired
    public void setChatFriendListService(ChatFriendListService chatFriendListService) {
        WebSocketUtils.chatFriendListService = chatFriendListService;
    }
    @Autowired
    public void setChatMsgService(ChatMsgService chatMsgService) {
        WebSocketUtils.chatMsgService = chatMsgService;
    }

    private static Logger log = LoggerFactory.getLogger(WebSocketUtils.class);


    /**
     * 用户上线时调用，修改数据库在线信息，清空未读信息
     *
     * @param senderId
     *
     */
    public ResponseEntity userOnline(String senderId) {
        ResponseEntity responseEntity = new ResponseEntity();
        ChatFriendListEntity chatFriendListEntity = new ChatFriendListEntity();
        chatFriendListEntity.setSenderId(senderId);
        //所有得对方的cfle 设置己方在线
        if(chatFriendListService.modifyOnline(chatFriendListEntity)) {
            responseEntity.setCode("200");
            responseEntity.setMessage("上线成功");
            return responseEntity;
        } else {
            responseEntity.setCode("500");
            responseEntity.setMessage("上线失败");
            return responseEntity;
        }
    }



    /**
     * 用户下线时调用,修改数据库在线信息
     * @param senderId
     *
     */
    public ResponseEntity userOffline(String senderId) {
        ResponseEntity responseEntity = new ResponseEntity();
        ChatFriendListEntity chatFriendListEntity = new ChatFriendListEntity();
        chatFriendListEntity.setSenderId(senderId);
        if(chatFriendListService.modifyOffline(chatFriendListEntity)) {
            responseEntity.setCode("200");
            responseEntity.setMessage("离线成功");
            //响应客户端
            return responseEntity;
        } else {
            responseEntity.setCode("500");
            responseEntity.setMessage("离线失败");
            //响应客户端
            return responseEntity;
        }

    }

    /**
     * 发送消息后将消息保存到数据库
     * @param chatMsgEntity
     */
    public ResponseEntity saveMsg(ChatMsgEntity chatMsgEntity) {
        ResponseEntity responseEntity = new ResponseEntity();

        //查询在线状况
        ChatFriendListEntity chatFriendListEntity = new ChatFriendListEntity();
        chatFriendListEntity.setLinkId(chatMsgEntity.getLinkId());
        List<ChatFriendListEntity> cfleList = chatFriendListService.selectTwoListByLinkId(chatFriendListEntity);


        if(chatMsgService.saveMessage(chatMsgEntity) == 1) {
            //若对方不在线 对方unread+1 加的是对方是sender的那项的unread ，若对方在线则不改
            for(ChatFriendListEntity c : cfleList) {
                if(c.getSenderId().equals(chatMsgEntity.getReceiverId())) {
                    if(c.getSenderIsOnline() == 0) {
                        c.setUnread(c.getUnread() + 1);
                        chatFriendListService.modify(c);
                    }
                }
            }
            responseEntity.setCode("200");
            responseEntity.setMessage("消息保存成功");
        } else {
            responseEntity.setCode("500");
            responseEntity.setMessage("消息保存失败");
        }
        //响应客户端
        return responseEntity;
    }


}
