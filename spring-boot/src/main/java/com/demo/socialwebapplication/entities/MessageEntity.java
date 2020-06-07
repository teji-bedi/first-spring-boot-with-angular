package com.demo.socialwebapplication.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

import static com.demo.socialwebapplication.entities.MessageEntity.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
public class MessageEntity {
    static final String TABLE_NAME = "MESSAGE";

    @Id
    @GeneratedValue
    @Column(name = "MESSAGE_ID")
    private Long messageId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
