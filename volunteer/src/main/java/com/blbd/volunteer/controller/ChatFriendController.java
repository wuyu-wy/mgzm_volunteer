package com.blbd.volunteer.controller;
import com.blbd.volunteer.dao.entity.ChatFriendListEntity;
import com.blbd.volunteer.dao.entity.ChatLinkEntity;
import com.blbd.volunteer.dao.entity.ChatMsgEntity;
import com.blbd.volunteer.dao.entity.LogEntity;
import com.blbd.volunteer.service.ChatFriendListService;
import com.blbd.volunteer.service.ChatLinkService;
import com.blbd.volunteer.service.ChatMsgService;
import com.blbd.volunteer.utils.HttpResponseEntity;
import com.blbd.volunteer.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/ChatFriend")
public class ChatFriendController {
    @Autowired
    ChatFriendListService chatFriendListService;
    @Autowired
    ChatLinkService chatLinkService;

    /**
     * 添加好友
     * @param chatFriendListEntity
     * @return
     */
    @PostMapping(value = "/addFriend", headers = "Accept=application/json")
    public HttpResponseEntity addFriend(@RequestBody ChatFriendListEntity chatFriendListEntity){
        ChatLinkEntity chatLinkEntity = new ChatLinkEntity();
        chatLinkEntity.setSenderId(chatFriendListEntity.getSenderId());
        chatLinkEntity.setReceiverId(chatFriendListEntity.getReceiverId());
        chatLinkEntity.setLinkId(UUIDUtil.getOneUUID());
        chatLinkEntity.setCreateTime(new Date(System.currentTimeMillis()));

        //设置CFLsender和receiver调换的实体,用于插入的第二条数据
        ChatFriendListEntity otherChatFriendListEntity = chatFriendListEntity;

        //添加linkId
        chatFriendListEntity.setLinkId(chatLinkEntity.getLinkId());
        otherChatFriendListEntity.setLinkId(chatLinkEntity.getLinkId());

        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        //先插入CLE
        if(chatLinkService.addFriendLink(chatLinkEntity) == 1) {
            httpResponseEntity.setMessage("添加Link成功");
        }else {
            httpResponseEntity.setCode("500");
            httpResponseEntity.setMessage("添加Link失败");
            return httpResponseEntity;
        }

        if(chatFriendListService.addFriendList(chatFriendListEntity) == 1 &&
        chatFriendListService.addFriendList(otherChatFriendListEntity) == 1) {
            httpResponseEntity.setCode("200");
            httpResponseEntity.setMessage("添加Link与List成功");
        } else {
            httpResponseEntity.setCode("500");
            httpResponseEntity.setMessage("添加Link成功，添加List失败");
            return httpResponseEntity;
        }

        return httpResponseEntity;
    }

    /**
     * //点击聊天时查询自己的单向好友列表，根据senderId查询
     * @param chatFriendListEntity
     * @return
     */
    @PostMapping(value = "/selectMyFriendList", headers = "Accept=application/json")
    public HttpResponseEntity selectMyFriendList(@RequestBody ChatFriendListEntity chatFriendListEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        List<ChatFriendListEntity> list = chatFriendListService.selectMyListBySenderId(chatFriendListEntity);

//        //设置未读数
//        setUnreadMsg(list);

        if(list.size() != 0) {
            httpResponseEntity.setCode("200");
            httpResponseEntity.setMessage("共查询到:" + list.size() + "条朋友列表");
            httpResponseEntity.setData(list);
        } else {
            httpResponseEntity.setCode("500");
            httpResponseEntity.setMessage("未查询到朋友列表信息");
        }
        return httpResponseEntity;
    }


    /**
     * 设置用户上线
     * @param chatFriendListEntity
     * @return HttpResponseEntity
     */
    @PostMapping(value = "/userOnline", headers = "Accept=application/json")
    public HttpResponseEntity userOnline(@RequestBody ChatFriendListEntity chatFriendListEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        int flag = 0; //falg =1 修改己方成功 =2 修改对方成功 =3修改双方成功

        //双向设置己方上线,设置己方未读归0
        List<ChatFriendListEntity> list = chatFriendListService.selectTwoListByLinkId(chatFriendListEntity);
        for(ChatFriendListEntity cfle : list) {
            //好友列表根据senderId查询，所以前端列表senderId都为用户Id
            if(cfle.getSenderId().equals(chatFriendListEntity.getSenderId())) {
                //己方的cfle 设置在线
                cfle.setSenderIsOnline(1);
                if(chatFriendListService.modify(cfle) == 1) {
                    flag++;
                } else {
                    httpResponseEntity.setCode("500");
                    httpResponseEntity.setMessage("己方list在线状态修改失败");
                    return httpResponseEntity;
                }
            } else {
                //所有其他的cfle 设置己方在线
                if(chatFriendListService.modifyOnline(chatFriendListEntity)) {
                    flag++;
                } else {
                    httpResponseEntity.setCode("500");
                    httpResponseEntity.setMessage("己方list在线状态修改成功，对方list在线状态修改失败");
                    return httpResponseEntity;
                }

            }
        }


        httpResponseEntity.setCode("200");
        httpResponseEntity.setMessage("上线成功");
        return httpResponseEntity;
    }

    /**
     * 置己方离线
     * @param chatFriendListEntity
     * @return
     */
    @PostMapping(value = "/userOffline", headers = "Accept=application/json")
    public HttpResponseEntity userOffline(@RequestBody ChatFriendListEntity chatFriendListEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        int flag = 0;
        //双向设置己方离线，设置对方latest消息
        List<ChatFriendListEntity> list = chatFriendListService.selectTwoListByLinkId(chatFriendListEntity);
        for(ChatFriendListEntity cfle : list) {
            //好友列表根据senderId查询，所以前端列表senderId都为用户Id
            if(cfle.getSenderId().equals(chatFriendListEntity.getSenderId())) {
                //己方的cfle 设置离线
                cfle.setSenderIsOnline(0);
                if(chatFriendListService.modify(cfle) == 1) {
                    flag++;
                } else {
                    httpResponseEntity.setCode("500");
                    httpResponseEntity.setMessage("己方list离线状态修改失败");
                    return httpResponseEntity;
                }
            } else {
                //设置其他关系中自己离线
                if( chatFriendListService.modifyOffline(chatFriendListEntity)) {
                    flag++;
                } else {
                    httpResponseEntity.setCode("500");
                    httpResponseEntity.setMessage("己方list离线状态修改成功，对方list离线状态修改失败");
                    return httpResponseEntity;
                }

            }
        }
        httpResponseEntity.setCode("200");
        httpResponseEntity.setMessage("离线成功");
        return httpResponseEntity;
    }

    /**
     * 根据LinkId双向删除好友
     * @param chatFriendListEntity
     * @return
     */
    @PostMapping(value = "/deleteFriendList", headers = "Accept=application/json")
    public HttpResponseEntity deleteFriendList(@RequestBody ChatFriendListEntity chatFriendListEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        if(chatFriendListService.deleteByLinkId(chatFriendListEntity) == 1) {
            httpResponseEntity.setCode("200");
            httpResponseEntity.setMessage("删除成功");
        } else {
            httpResponseEntity.setCode("500");
            httpResponseEntity.setMessage("删除失败");
        }
        return httpResponseEntity;
    }

    /**
     * //修改信息，可修改项receiver_picture、senderIsOnline、receiverIsOnline、unread、status
     * @param chatFriendListEntity
     * @return
     */
    @PostMapping(value = "/modifyFriendList", headers = "Accept=application/json")
    public HttpResponseEntity modifyFriendList(@RequestBody ChatFriendListEntity chatFriendListEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        if(chatFriendListService.modify(chatFriendListEntity) == 1) {
            httpResponseEntity.setCode("200");
            httpResponseEntity.setMessage("修改成功");
        } else {
            httpResponseEntity.setCode("500");
            httpResponseEntity.setMessage("修改失败");
        }
        return httpResponseEntity;
    }


//    @Autowired
//    ChatMsgService chatMsgService;
//    //查询并设置未读消息
//    public void setUnreadMsg(List<ChatFriendListEntity> list) {
//        ChatMsgEntity chatMsgEntity = new ChatMsgEntity();
//        List<ChatMsgEntity> msgList = new ArrayList<>();
//
//        //根据好友列表循环
//        for(ChatFriendListEntity e: list) {
//            chatMsgEntity.setLinkId(e.getLinkId());
//            //查询于每个好友的消息
//            msgList = chatMsgService.selectMessageByLinkId(chatMsgEntity);
//            //发送方为对方，且消息为最晚消息时开始计数
//            int unread = 0;
//            boolean flag = false;
//            //循环于当前好友的每条消息
//            for(ChatMsgEntity msg : msgList) {
//                if(msg.getSenderId().equals(e.getReceiverId()) && msg.getIsLatest() == 1) {
//                    flag = true;
//                }
//                if (flag && msg.getSenderId().equals(e.getReceiverId())){
//                    unread++;
//                }
//            }
//            //与当前好友msg循环完后设置对当前好友的未读数
//            e.setUnread(unread);
//        }
//    }






}
