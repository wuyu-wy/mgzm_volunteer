package com.blbd.volunteer.service;

import com.blbd.volunteer.dao.entity.ChatMsgEntity;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author 1185911254@qq.com
* @description 针对表【chat_msg】的数据库操作Service
* @createDate 2023-11-05 15:51:24
*/

public interface ChatMsgService {
    //    保存消息
    int saveMessage(ChatMsgEntity chatMsgEntity);
    //    删除消息
    int deleteMessageByMsgId(ChatMsgEntity chatMsgEntity);
    //    修改消息为最后一条
    int setMessageLatest(ChatMsgEntity chatMsgEntity);
    //根据linkId查询所有消息
    List<ChatMsgEntity> selectMessageByLinkId(ChatMsgEntity chatMsgEntity);


}
