package org.com.business.spring.boot.fw.persistence.entity;

import java.io.Serializable;

public class MessageInfoKey implements Serializable {
    private String messageId;

    private String userId;

    private static final long serialVersionUID = 1L;

    public String getMessageId() {
        return messageId;
    }

    public MessageInfoKey withMessageId(String messageId) {
        this.setMessageId(messageId);
        return this;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public MessageInfoKey withUserId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", messageId=").append(messageId);
        sb.append(", userId=").append(userId);
        sb.append("]");
        return sb.toString();
    }
}