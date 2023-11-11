package com.blbd.volunteer.controller;

import com.blbd.volunteer.dao.entity.ChatFriendListEntity;
import com.blbd.volunteer.dao.entity.ChatMsgEntity;
import com.blbd.volunteer.service.ChatFriendListService;
import com.blbd.volunteer.service.ChatMsgService;
import com.blbd.volunteer.utils.HttpResponseEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ChatMsgController
 * 用于处理用户聊天消息的增删改查
 *
 * @module mgzm_volunteer
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/ChatMsg")
public class ChatMsgController {
    @Autowired
    ChatMsgService chatMsgService;

    @Autowired
    ChatFriendListService chatFriendListService;


    /**
     * 发送消息时保存消息
     * @param chatMsgEntity
     * @return
     */
    @PostMapping(value = "/saveMsg", headers = "Accept=application/json")
    public HttpResponseEntity saveMsg(@RequestBody ChatMsgEntity chatMsgEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

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
            httpResponseEntity.setCode("200");
            httpResponseEntity.setMessage("消息保存成功");
        } else {
            httpResponseEntity.setCode("500");
            httpResponseEntity.setMessage("消息保存失败");
        }
        return httpResponseEntity;
    }

    /**
     * 根据MsgId删除消息
     * @param chatMsgEntity
     * @return
     */
    @PostMapping(value = "/deleteMsg", headers = "Accept=application/json")
    public HttpResponseEntity deleteMsg(@RequestBody ChatMsgEntity chatMsgEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        if(chatMsgService.deleteMessageByMsgId(chatMsgEntity) == 1) {
            httpResponseEntity.setCode("200");
            httpResponseEntity.setMessage("消息删除成功");
        } else {
            httpResponseEntity.setCode("500");
            httpResponseEntity.setMessage("消息删除失败");
        }
        return httpResponseEntity;
    }

    /**
     * 根据linkId查询双方消息，同时将对方最后一条消息设置为latest消息 参数中senderId为己方Id receiverId为对方Id,
     * 查询完后设置己方（senderId）未读数归0
     * 并设置对方最后一条消息为最新消息latest
     * @param chatMsgEntity
     * @return
     */
    @PostMapping(value = "/selectMsgByLinkId", headers = "Accept=application/json")
    public HttpResponseEntity selectMsgByLinkId(@RequestBody ChatMsgEntity chatMsgEntity,
                                                @RequestParam("PageNum") Integer pageNum,
                                                @RequestParam("PageSize") Integer pageSize) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        PageHelper.startPage(pageNum,pageSize);
        List<ChatMsgEntity> list = chatMsgService.selectMessageByLinkId(chatMsgEntity);
        PageInfo<ChatMsgEntity> pageInfo = new PageInfo<>(list);

        //修改对方的最新消息标识位
        boolean flag = false;
        for(int i = list.size()-1; i>0; i--) {
            if(!flag) {
                //从后往前查找到对方第一条消息,设置islatest为1
                if(list.get(i).getSenderId().equals(chatMsgEntity.getReceiverId())) {
                    list.get(i).setIsLatest(1);
                    chatMsgService.setMessageLatest(list.get(i));
                    flag = true;
                    continue;
                }
            } else  {
                //继续找到对方之前的最后一条消息，设置islatest为0
                if(list.get(i).getSenderId().equals(chatMsgEntity.getReceiverId()) && list.get(i).getIsLatest() == 1) {
                    list.get(i).setIsLatest(0);
                    chatMsgService.setMessageLatest(list.get(i));
                    //设置完毕，跳出循环
                    break;
                }
            }
        }

        //设置己方unread归0
        ChatFriendListEntity chatFriendListEntity = new ChatFriendListEntity();
        chatFriendListEntity.setLinkId(chatMsgEntity.getLinkId());
        List<ChatFriendListEntity> cFList = chatFriendListService.selectTwoListByLinkId(chatFriendListEntity);
        for(ChatFriendListEntity cfle : cFList) {
            if(cfle.getSenderId().equals(chatMsgEntity.getSenderId())) {
                cfle.setUnread(0);
                chatFriendListService.modify(cfle);
            }
        }

        //传输消息列表
        if(list.size() != 0) {
            httpResponseEntity.setCode("200");
            httpResponseEntity.setMessage("消息查询成功");
            Map resultMap = new ConcurrentHashMap();
            resultMap.put("PageInfo", pageInfo);
            resultMap.put("list",list);
            httpResponseEntity.setData(resultMap);
        } else {
            httpResponseEntity.setCode("500");
            httpResponseEntity.setMessage("消息查询失败");
        }
        return httpResponseEntity;
    }

    /**
     * //用户离开聊天界面时调用，设置对方的最后一条消息为最后一条，用于已读未读消息
     * @param chatMsgEntity
     * @return
     */
    @PostMapping(value = "/setMsgLatest", headers = "Accept=application/json")
    public HttpResponseEntity setMsgLatest(@RequestBody ChatMsgEntity chatMsgEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        if(chatMsgService.setMessageLatest(chatMsgEntity) == 1) {
            httpResponseEntity.setCode("200");
            httpResponseEntity.setMessage("设置最后一条成功");
        } else {
            httpResponseEntity.setCode("500");
            httpResponseEntity.setMessage("设置最后一条失败");
        }
        return httpResponseEntity;
    }


}
