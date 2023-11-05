package com.blbd.volunteer.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * @description 好友关系主表
 *添加好友后只向表中添加一条数据
 */
@Data
public class ChatLinkEntity {

    //关系表id
    private String linkId;

    //发送者
    private String senderId;

    //接收者
    private String receiverId;

    //创建时间
    private Date createTime;


}
