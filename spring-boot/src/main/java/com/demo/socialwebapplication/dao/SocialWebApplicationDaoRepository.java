package com.demo.socialwebapplication.dao;

import com.demo.socialwebapplication.model.Message;
import com.demo.socialwebapplication.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@Repository
public class SocialWebApplicationDaoRepository {
    private static Map<Long, User> userIdToUserMapping = new HashMap<>();
    private static Map<String, User> userNameToUserMapping = new HashMap<>();
    private static List<Message> messages = newArrayList();


    public long getMessageCount() {
        return messages.size();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getAllMessages() {
        Collections.sort(messages);
        return messages;
    }

    public User getOrCreateUserByUserName(String userName) {
        if (userNameToUserMapping.get(userName) == null) {
            User user = new User();
            user.setUserName(userName);
            user.setUserId(userNameToUserMapping.size() + 1);
            userNameToUserMapping.put(userName, user);
        }
        return userNameToUserMapping.get(userName);
    }

    public User getUserByUserName(String userName) {
        return userNameToUserMapping.get(userName);
    }

    public void addFollower(String userName, String followingUserName) {
        User user = userNameToUserMapping.get(userName);
        user.getFollowingUserNames().add(followingUserName);
        userNameToUserMapping.put(userName, user);
        userNameToUserMapping.get(userName).getFollowingUserNames().add(followingUserName);
    }

    public List<User> getAllAvailableUsers() {
        return newArrayList(userNameToUserMapping.values());
    }
}
