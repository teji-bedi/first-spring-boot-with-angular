package com.demo.socialwebapplication.service;

import com.demo.socialwebapplication.dao.SocialWebApplicationDaoRepository;
import com.demo.socialwebapplication.model.Message;
import com.demo.socialwebapplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.toSet;

@Service
public class SocialWebApplicationService {

    @Autowired
    private SocialWebApplicationDaoRepository repository;

    public void addMessage(User user, String postedMessage) {
        Message message = new Message();
        message.setUserId(user.getUserId());
        message.setUserName(user.getUserName());
        message.setMessageCreatedAt(new Date());
        message.setMessageId(repository.getMessageCount() + 1);
        message.setMessage(postedMessage);

        repository.addMessage(message);
    }

    public List<Message> getAllMessages() {
        return repository.getAllMessages();
    }

    public User getUserByUserName(String userName) {
        return repository.getUserByUserName(userName);
    }

    public User getOrCreateUserByUserName(String userName) {
        return repository.getOrCreateUserByUserName(userName);
    }


    public void addFollower(String userName, String followingUserName) {
        repository.addFollower(userName, followingUserName);
    }

    public List<Message> getMessagesByUserName(String userName) {
        List<Message> messagesByUserName = repository.getAllMessages().stream().filter(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                return message.getUserName().equals(userName);
            }
        }).collect(Collectors.toList());
        return messagesByUserName;
    }

    public List<Message> getMessagesFromFollowing(User user) {
        if(user==null){
            return newArrayList();
        }
        List<Message> messagesByUserName = repository.getAllMessages().stream().filter(new Predicate<Message>() {
            @Override
            public boolean test(Message message) {
                return user.getFollowingUserNames().contains(message.getUserName());
            }
        }).collect(Collectors.toList());
        return messagesByUserName;
    }

    public Set<User> getAvailableUsersToFollowForUserName(String userName) {
        User sourceUser = repository.getUserByUserName(userName);
        if(sourceUser==null){
            return newHashSet();
        }
        Set<User> availableUsers = repository.getAllAvailableUsers().stream().filter(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return !sourceUser.getFollowingUserNames().contains(user.getUserName());
            }
        }).collect(toSet());
        availableUsers.remove(sourceUser);

        return availableUsers;
    }

    public boolean checkForValidUserName(String userName) {
        User user = repository.getUserByUserName(userName);
        return user != null;
    }
}
