package com.demo.socialwebapplication.service;

import com.demo.socialwebapplication.dao.SocialWebApplicationDaoRepository;
import com.demo.socialwebapplication.model.MessageDto;
import com.demo.socialwebapplication.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class SocialWebApplicationService {

    @Autowired
    private SocialWebApplicationDaoRepository repository;

    public void addMessage(UserDto user, String postedMessage) {
        MessageDto messageDto = new MessageDto();
        messageDto.setUserId(user.getUserId());
        messageDto.setMessageId(repository.getMessageCount() + 1);
        messageDto.setMessage(postedMessage);

        repository.addMessage(messageDto);
    }

    public List<MessageDto> getAllMessages() {
        return repository.getAllMessages();
    }

    public UserDto getUserByUserName(String userName) {
        return repository.getUserByUserName(userName);
    }

    public UserDto getOrCreateUserByUserName(String userName) {
        return repository.getOrCreateUserByUserName(userName);
    }


    public void addFollower(String userName, String followingUserName) {
        repository.addFollower(userName, followingUserName);
    }

    public List<MessageDto> getMessagesByUserName(String userName) {
//        List<MessageDto> messagesByUserName = repository.getAllMessages().stream().filter(message -> message.getUserName().equals(userName)).collect(toList());
//        return messagesByUserName;
        return null;
    }

    public List<MessageDto> getMessagesFromFollowing(UserDto user) {
//        if(user==null){
//            return newArrayList();
//        }
//        List<MessageDto> messagesByUserName = repository.getAllMessages().stream().filter(message -> user.getFollowingUserNames().contains(message.getUserName())).collect(toList());
//        return messagesByUserName;

        return null;
    }

    public Set<UserDto> getAvailableUsersToFollowForUserName(String userName) {
//        UserDto sourceUser = repository.getUserByUserName(userName);
//        if(sourceUser==null){
//            return newHashSet();
//        }
//        Set<UserDto> availableUsers = repository.getAllAvailableUsers().stream().filter(user -> !sourceUser.getFollowingUserNames().contains(user.getUserName())).collect(toSet());
//        availableUsers.remove(sourceUser);
//
//        return availableUsers;
        return null;
    }

    public boolean checkForValidUserName(String userName) {
        UserDto user = repository.getUserByUserName(userName);
        return user != null;
    }
}
