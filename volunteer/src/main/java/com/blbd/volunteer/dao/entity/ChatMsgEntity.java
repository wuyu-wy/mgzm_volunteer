package com.blbd.volunteer.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * 承接websocket发送来的消息
 */
@Data
public class ChatMsgEntity {
    /**
     * 信息id（自增）
     */
    private int messageId;
    /**
     * 关系ID
     */
    private int linkId;

    /**
     * 消息类型：0心跳 1登录 2文字 3图片 4文件
     * HEART_BEAT,LOGIN,TEXT_MESSAGE,IMAGE,FILE
     */
    private int msgType;

    /**
     * 发送用户ID
     */
    private String senderId;

    /**
     * 接受用户ID
     */
    private String receiverId;

    /**
     * 消息内容
     * textMessage:文字消息
     */
    private String msgBody;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 是否为最后一条
     */
    private Boolean isLatest;
}