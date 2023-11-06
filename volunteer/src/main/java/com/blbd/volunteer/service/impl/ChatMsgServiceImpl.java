package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.entity.ChatMsgEntity;
import com.blbd.volunteer.service.ChatMsgService;
import com.blbd.volunteer.dao.ChatMsgEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【chat_msg】的数据库操作Service实现
* @createDate 2023-11-05 15:51:24
*/
@Service
public class ChatMsgServiceImpl implements ChatMsgService {
    @Autowired
    ChatMsgEntityMapper chatMsgEntityMapper;
    //    保存消息
    public int saveMessage(ChatMsgEntity chatMsgEntity){
        if(chatMsgEntityMapper.insert(chatMsgEntity) == 1) {
            return 1;
        } else return 0;
    }

    //    删除消息
    public int deleteMessageByMsgId(ChatMsgEntity chatMsgEntity){
        if(chatMsgEntityMapper.deleteByMessageId(chatMsgEntity) == 1) {
            return 1;
        } else return 0;
    }

    //    修改消息为最后一条或不是最后一条
    public int setMessageLatest(ChatMsgEntity chatMsgEntity){
        if(chatMsgEntityMapper.modify(chatMsgEntity) == 1) {
            return 1;
        } else return 0;
    }

    //根据linkId查询所有消息
    public List<ChatMsgEntity> selectMessageByLinkId(ChatMsgEntity chatMsgEntity){
        return chatMsgEntityMapper.selectByLinkId(chatMsgEntity);
    }
}




