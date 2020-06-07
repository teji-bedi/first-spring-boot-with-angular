package com.demo.socialwebapplication.dao;

import com.demo.socialwebapplication.model.MessageDto;
import com.demo.socialwebapplication.model.UserDto;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@Repository
public class SocialWebApplicationDaoRepository {
    private static Map<Long, UserDto> userIdToUserMapping = new HashMap<>();
    private static Map<String, UserDto> userNameToUserMapping = new HashMap<>();
    private static List<MessageDto> messageDtos = newArrayList();


    public long getMessageCount() {
        return messageDtos.size();
    }

    public void addMessage(MessageDto messageDto) {
        messageDtos.add(messageDto);
    }

    public List<MessageDto> getAllMessages() {
        Collections.sort(messageDtos);
        return messageDtos;
    }

    public UserDto getOrCreateUserByUserName(String userName) {
        if (userNameToUserMapping.get(userName) == null) {
            UserDto user = new UserDto();
            user.setUserName(userName);
            user.setUserId(userNameToUserMapping.size() + 1);
            userNameToUserMapping.put(userName, user);
        }
        return userNameToUserMapping.get(userName);
    }

    public UserDto getUserByUserName(String userName) {
        return userNameToUserMapping.get(userName);
    }

    public void addFollower(String userName, String followingUserName) {
        UserDto user = userNameToUserMapping.get(userName);
        userNameToUserMapping.put(userName, user);
    }

    public List<UserDto> getAllAvailableUsers() {
        return newArrayList(userNameToUserMapping.values());
    }
}
