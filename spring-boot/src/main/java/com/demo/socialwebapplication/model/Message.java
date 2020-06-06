package com.demo.socialwebapplication.model;

import java.util.Date;

public class Message implements Comparable<Message> {
    private long messageId;
    private long userId;
    private String userName;
    private String message;
    private Date messageCreatedAt;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageCreatedAt() {
        return messageCreatedAt;
    }

    public void setMessageCreatedAt(Date messageCreatedAt) {
        this.messageCreatedAt = messageCreatedAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int compareTo(Message o) {
        return o.getMessageCreatedAt().compareTo(getMessageCreatedAt());
    }
}
