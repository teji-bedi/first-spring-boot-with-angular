package com.demo.socialwebapplication.model;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;


public class User {
    private long userId;
    private String userName;
    private Set<String> followingUserNames = newHashSet();

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

    public Set<String> getFollowingUserNames() {  return followingUserNames; }

    public void setFollowingUserNames(Set<String> followingUserNames) { this.followingUserNames = followingUserNames; }
}
