package com.blbd.volunteer.dao;

import com.blbd.volunteer.dao.entity.ChatFriendListEntity;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【chat_friend_list】的数据库操作Mapper
* @createDate 2023-11-05 15:51:06
* @Entity com.blbd.volunteer.dao.entity.ChatFriendListEntity
*/
@Mapper
@Repository
public interface ChatFriendListEntityMapper  {
    int insert(ChatFriendListEntity chatFriendListEntity);
    int deleteByLinkId(ChatFriendListEntity chatFriendListEntity);
    int modify(ChatFriendListEntity chatFriendListEntity);
    List<ChatFriendListEntity> selectBySenderId(ChatFriendListEntity chatFriendListEntity);
    List<ChatFriendListEntity> selectByLinkId(ChatFriendListEntity chatFriendListEntity);

    List<ChatFriendListEntity> selectByReceiverId(ChatFriendListEntity chatFriendListEntity);

}




