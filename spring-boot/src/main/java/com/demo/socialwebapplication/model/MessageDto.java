package com.demo.socialwebapplication.model;

import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel("Contains everything for message to be shown on UI")
public class MessageDto implements Comparable<MessageDto> {
    private Long messageId;
    private Long userId;
    private String userName;
    private String message;
    private Date createdAt;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int compareTo(MessageDto o) {
        return o.getCreatedAt().compareTo(getCreatedAt());
    }
}
