package com.blbd.volunteer.dao;

import com.blbd.volunteer.dao.entity.ChatMsgEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
* @author 1185911254@qq.com
* @description 针对表【chat_msg】的数据库操作Mapper
* @createDate 2023-11-05 15:51:24
* @Entity com.blbd.volunteer.dao.entity.ChatMsgEntity
*/
@Mapper
@Repository
public interface ChatMsgEntityMapper {
    int insert(ChatMsgEntity chatMsgEntity);
    int deleteByMessageId(ChatMsgEntity chatMsgEntity);
    int modify(ChatMsgEntity chatMsgEntity);
    List<ChatMsgEntity> selectByLinkId(ChatMsgEntity chatMsgEntity);



}




