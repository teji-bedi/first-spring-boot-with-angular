package com.demo.socialwebapplication.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.demo.socialwebapplication.entities.UserFollowEntity.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
public class UserFollowEntity {
    public static final String TABLE_NAME = "USER_FOLLOW";


    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "FOLLOWING_USER_ID")
    private Long followingUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(Long followingUserId) {
        this.followingUserId = followingUserId;
    }
}
