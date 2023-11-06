package com.blbd.volunteer.dao.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName chat_friend_list
 */
/**
 * @description 聊天列表表（用户的聊天列表）
 * a与b加上好友后添加两条数据
 * 双向关系，添加好友后向表中添加两条数据a->b and b->a
 */
@Data
public class ChatFriendListEntity implements Serializable {
    /**
     * 聊天列表主键
     */
    private Integer listId;

    /**
     * 聊天主表id
     */
    private String linkId;

    /**
     * 发送者
     */
    private String senderId;

    /**
     * 接收者
     */
    private String receiverId;

    /**
     * 接收者头像
     */
    private String receiverPicture;

    /**
     * 发送方是否在窗口
     */
    private Integer senderIsOnline;

    /**
     * 接收方是否在窗口
     */
    private Integer receiverIsOnline;

    /**
     * 未读数
     */
    private Integer unread;

    /**
     * 是否删除
     */
    private Integer status;

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
        ChatFriendListEntity other = (ChatFriendListEntity) that;
        return (this.getListId() == null ? other.getListId() == null : this.getListId().equals(other.getListId()))
            && (this.getLinkId() == null ? other.getLinkId() == null : this.getLinkId().equals(other.getLinkId()))
            && (this.getSenderId() == null ? other.getSenderId() == null : this.getSenderId().equals(other.getSenderId()))
            && (this.getReceiverId() == null ? other.getReceiverId() == null : this.getReceiverId().equals(other.getReceiverId()))
            && (this.getReceiverPicture() == null ? other.getReceiverPicture() == null : this.getReceiverPicture().equals(other.getReceiverPicture()))
            && (this.getSenderIsOnline() == null ? other.getSenderIsOnline() == null : this.getSenderIsOnline().equals(other.getSenderIsOnline()))
            && (this.getReceiverIsOnline() == null ? other.getReceiverIsOnline() == null : this.getReceiverIsOnline().equals(other.getReceiverIsOnline()))
            && (this.getUnread() == null ? other.getUnread() == null : this.getUnread().equals(other.getUnread()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getListId() == null) ? 0 : getListId().hashCode());
        result = prime * result + ((getLinkId() == null) ? 0 : getLinkId().hashCode());
        result = prime * result + ((getSenderId() == null) ? 0 : getSenderId().hashCode());
        result = prime * result + ((getReceiverId() == null) ? 0 : getReceiverId().hashCode());
        result = prime * result + ((getReceiverPicture() == null) ? 0 : getReceiverPicture().hashCode());
        result = prime * result + ((getSenderIsOnline() == null) ? 0 : getSenderIsOnline().hashCode());
        result = prime * result + ((getReceiverIsOnline() == null) ? 0 : getReceiverIsOnline().hashCode());
        result = prime * result + ((getUnread() == null) ? 0 : getUnread().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", listId=").append(listId);
        sb.append(", linkId=").append(linkId);
        sb.append(", senderId=").append(senderId);
        sb.append(", receiverId=").append(receiverId);
        sb.append(", receiverPicture=").append(receiverPicture);
        sb.append(", senderIsOnline=").append(senderIsOnline);
        sb.append(", receiverIsOnline=").append(receiverIsOnline);
        sb.append(", unread=").append(unread);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}