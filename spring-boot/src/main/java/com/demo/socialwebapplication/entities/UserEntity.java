package com.demo.socialwebapplication.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.demo.socialwebapplication.entities.UserEntity.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
public class UserEntity {
    static final String TABLE_NAME = "USER";

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "USER_NAME", length = 50)
    private String userName;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
