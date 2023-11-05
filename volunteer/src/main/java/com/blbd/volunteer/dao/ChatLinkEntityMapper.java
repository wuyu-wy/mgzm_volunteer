package com.blbd.volunteer.dao;

import com.blbd.volunteer.dao.entity.ChatLinkEntity;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【chat_link】的数据库操作Mapper
* @createDate 2023-11-05 15:51:17
* @Entity com.blbd.volunteer.dao.entity.ChatLinkEntity
*/
@Mapper
@Repository
public interface ChatLinkEntityMapper {
    int insert(ChatLinkEntity chatLinkEntity);
    int deleteByLinkId(ChatLinkEntity chatLinkEntity);
    List<ChatLinkEntity> selectByLinkId(ChatLinkEntity chatLinkEntity);

}




