package com.blbd.volunteer.dao.entity;

import lombok.Data;

/**
 * @description 聊天列表表（用户的聊天列表）
 * a与b加上好友后添加两条数据
 * 双向关系，添加好友后向表中添加两条数据a->b and b->a
 */
@Data
public class ChatFriendListEntity {

    //列表id（自增）
    private int listId;

    //关系表主键
    private String linkId;

    //发送者
    private String senderId;

    //接收者
    private String receiverId;

    //接收者的图片
    private String receiverPicture;

    //发送者是否在窗口
    private Boolean senderIsOnline;

    //接收者是否在窗口
    private Boolean receiverIsOnline;

    //未读数 fromUser的未读数
    private int unread;

    //是否被删除
    private Boolean status;


}

