package com.blbd.volunteer.service;

import com.blbd.volunteer.dao.entity.ChatFriendListEntity;

import java.util.List;


/**
* @author 1185911254@qq.com
* @description 针对表【chat_friend_list】的数据库操作Service
* @createDate 2023-11-05 15:51:06
*/
public interface ChatFriendListService {

    //    添加至好友列表（双向添加）
    int addFriendList(ChatFriendListEntity chatFriendListEntity);

    //根据linkId删除好友列表（双向删除）
    int deleteByLinkId(ChatFriendListEntity chatFriendListEntity);

    //修改好友列表信息
    int modify(ChatFriendListEntity chatFriendListEntity);

    //通过用户id查询好友列表
    List<ChatFriendListEntity> selectMyListBySenderId(ChatFriendListEntity chatFriendListEntity);

    //通过linkId查询双向好友列表
    List<ChatFriendListEntity> selectTwoListByLinkId(ChatFriendListEntity chatFriendListEntity);

}
