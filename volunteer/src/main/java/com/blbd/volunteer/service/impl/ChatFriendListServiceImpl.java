package com.blbd.volunteer.service.impl;


import com.blbd.volunteer.dao.entity.ChatFriendListEntity;
import com.blbd.volunteer.service.ChatFriendListService;
import com.blbd.volunteer.dao.ChatFriendListEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【chat_friend_list】的数据库操作Service实现
* @createDate 2023-11-05 15:51:06
*/
@Service
public class ChatFriendListServiceImpl implements ChatFriendListService {
    @Autowired
    ChatFriendListEntityMapper chatFriendListEntityMapper;

    //    添加至好友列表（双向添加）
    public int addFriendList(ChatFriendListEntity chatFriendListEntity){
        if(chatFriendListEntityMapper.insert(chatFriendListEntity) == 1) {
            return 1;
        }else return 0;
    }

    //根据linkId删除好友列表（双向删除）
    public int deleteByLinkId(ChatFriendListEntity chatFriendListEntity){
        if(chatFriendListEntityMapper.deleteByLinkId(chatFriendListEntity) == 1) {
            return 1;
        }else return 0;
    }

    //修改好友列表信息
    public int modify(ChatFriendListEntity chatFriendListEntity){
        if(chatFriendListEntityMapper.modify(chatFriendListEntity) == 1) {
            return 1;
        }else return 0;
    }

    //通过用户id查询好友列表
    public List<ChatFriendListEntity> selectMyListBySenderId(ChatFriendListEntity chatFriendListEntity){
        return chatFriendListEntityMapper.selectBySenderId(chatFriendListEntity);
    }

    //通过Linkid查询双向好友列表信息
    public List<ChatFriendListEntity> selectTwoListByLinkId(ChatFriendListEntity chatFriendListEntity){
        return chatFriendListEntityMapper.selectByLinkId(chatFriendListEntity);
    }


    //用户上线，修改所有receiver为用户id的在线信息
    public boolean modifyOnline(ChatFriendListEntity chatFriendListEntity) {
        chatFriendListEntity.setReceiverId(chatFriendListEntity.getSenderId());
        List<ChatFriendListEntity> list = chatFriendListEntityMapper.selectByReceiverId(chatFriendListEntity);
        for(ChatFriendListEntity e: list) {
            e.setReceiverIsOnline(1);
            if(chatFriendListEntityMapper.modify(e) == 0) {
                return false;
            }
        }

        return true;
    }

    //用户下线，修改所有receiver为用户id的在线信息
    public boolean modifyOffline(ChatFriendListEntity chatFriendListEntity) {
        chatFriendListEntity.setReceiverId(chatFriendListEntity.getSenderId());
        List<ChatFriendListEntity> list = chatFriendListEntityMapper.selectByReceiverId(chatFriendListEntity);
        for(ChatFriendListEntity e: list) {
            e.setReceiverIsOnline(0);
            if(chatFriendListEntityMapper.modify(e) == 0) {
                return false;
            }
        }

        return true;
    }


}




