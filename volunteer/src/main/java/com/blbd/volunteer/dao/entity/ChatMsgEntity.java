package com.blbd.volunteer.dao.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName chat_msg
 */
@Data
public class ChatMsgEntity implements Serializable {
    /**
     * 聊天内容id
     */
    private Integer messageId;

    /**
     * 聊天主表id
     */
    private String linkId;

    /**
     * 消息类型：0心跳 1上线 2文字 3图片 4文件
     * HEART_BEAT,ONLINE,TEXT_MESSAGE,IMAGE,FILE
     */
    private Integer msgType;

    /**
     * 发送者
     */
    private String senderId;

    /**
     * 接收者
     */
    private String receiverId;

    /**
     * 聊天内容
     */
    private String msgBody;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 是否为最后一条信息
     */
    private Integer isLatest = 0;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ChatMsgEntity other = (ChatMsgEntity) that;
        return (this.getMessageId() == null ? other.getMessageId() == null : this.getMessageId().equals(other.getMessageId()))
            && (this.getLinkId() == null ? other.getLinkId() == null : this.getLinkId().equals(other.getLinkId()))
            && (this.getMsgType() == null ? other.getMsgType() == null : this.getMsgType().equals(other.getMsgType()))
            && (this.getSenderId() == null ? other.getSenderId() == null : this.getSenderId().equals(other.getSenderId()))
            && (this.getReceiverId() == null ? other.getReceiverId() == null : this.getReceiverId().equals(other.getReceiverId()))
            && (this.getMsgBody() == null ? other.getMsgBody() == null : this.getMsgBody().equals(other.getMsgBody()))
            && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()))
            && (this.getIsLatest() == null ? other.getIsLatest() == null : this.getIsLatest().equals(other.getIsLatest()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMessageId() == null) ? 0 : getMessageId().hashCode());
        result = prime * result + ((getLinkId() == null) ? 0 : getLinkId().hashCode());
        result = prime * result + ((getMsgType() == null) ? 0 : getMsgType().hashCode());
        result = prime * result + ((getSenderId() == null) ? 0 : getSenderId().hashCode());
        result = prime * result + ((getReceiverId() == null) ? 0 : getReceiverId().hashCode());
        result = prime * result + ((getMsgBody() == null) ? 0 : getMsgBody().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        result = prime * result + ((getIsLatest() == null) ? 0 : getIsLatest().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", messageId=").append(messageId);
        sb.append(", linkId=").append(linkId);
        sb.append(", msgType=").append(msgType);
        sb.append(", senderId=").append(senderId);
        sb.append(", receiverId=").append(receiverId);
        sb.append(", msgBody=").append(msgBody);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", isLatest=").append(isLatest);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}