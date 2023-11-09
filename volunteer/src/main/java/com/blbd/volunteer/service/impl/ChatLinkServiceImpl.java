package com.blbd.volunteer.service.impl;


import com.blbd.volunteer.dao.entity.ChatLinkEntity;
import com.blbd.volunteer.service.ChatLinkService;
import com.blbd.volunteer.dao.ChatLinkEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【chat_link】的数据库操作Service实现
* @createDate 2023-11-05 15:51:17
*/
@Service
public class ChatLinkServiceImpl implements ChatLinkService {
    @Autowired ChatLinkEntityMapper chatLinkEntityMapper;
    //添加好友关系
    public int addFriendLink(ChatLinkEntity chatLinkEntity){
        if(chatLinkEntityMapper.insert(chatLinkEntity) == 1) {
            return 1;
        } else return 0;

    }

    //通过linkId删除关系
    public int deleteLinkByLinkId(ChatLinkEntity chatLinkEntity){
        if(chatLinkEntityMapper.deleteByLinkId(chatLinkEntity) == 1) {
            return 1;
        } else return 0;
    }

    //通过LinkId查询关系信息
    public List<ChatLinkEntity> selectLinkByLinkId(ChatLinkEntity chatLinkEntity){
        return chatLinkEntityMapper.selectByLinkId(chatLinkEntity);
    }

}




