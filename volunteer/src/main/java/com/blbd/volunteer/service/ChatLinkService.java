package com.blbd.volunteer.service;

import com.blbd.volunteer.dao.entity.ChatLinkEntity;

import java.util.List;


/**
* @author 1185911254@qq.com
* @description 针对表【chat_link】的数据库操作Service
* @createDate 2023-11-05 15:51:17
*/
public interface ChatLinkService {
    //添加好友关系
    int addFriendLink(ChatLinkEntity chatLinkEntity);

    //通过linkId删除关系
    int deleteLinkByLinkId(ChatLinkEntity chatLinkEntity);

    //通过LinkId查询关系信息
    List<ChatLinkEntity> selectLinkByLinkId(ChatLinkEntity chatLinkEntity);

}
