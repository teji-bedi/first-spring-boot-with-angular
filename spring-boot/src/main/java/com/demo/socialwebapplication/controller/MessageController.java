package com.demo.socialwebapplication.controller;

import com.demo.socialwebapplication.model.Message;
import com.demo.socialwebapplication.model.User;
import com.demo.socialwebapplication.service.SocialWebApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessageController {

    @Autowired
    private SocialWebApplicationService service;

    @PostMapping(path = "/postMessage/{userName}")
    public void postMessage(@PathVariable String userName, @RequestBody String messagePosted) {
        User user = service.getOrCreateUserByUserName(userName);
        service.addMessage(user, messagePosted);
    }

    @GetMapping(path = "/getMessagesByUserName/{userName}")
    public List<Message> getMessagesByUserName(@PathVariable String userName) {
        return service.getMessagesByUserName(userName);
    }

    @GetMapping(path = "/getMessagesFromFollowing/{userName}")
    public List<Message> getMessagesFromFollowing(@PathVariable String userName) {
        User user = service.getUserByUserName(userName);
        return service.getMessagesFromFollowing(user);
    }

    @PostMapping(path = "/addFollowing/{userName}/{followingUserName}")
    public void addFollower(@PathVariable String userName, @PathVariable String followingUserName) {
        service.addFollower(userName, followingUserName);
    }

    @GetMapping(path = "/getAvailableUsersToFollowForUserName/{userName}")
    public Set<User> getAvailableUsersToFollowForUserName(@PathVariable String userName) {
        return service.getAvailableUsersToFollowForUserName(userName);
    }

    @GetMapping(path = "/checkForValidUserName/{userName}")
    public boolean checkForValidUserName(@PathVariable String userName) {
        return service.checkForValidUserName(userName);
    }

    @GetMapping(path = "/getAllMessages")
    public List<Message> getAllMessages() {
        return service.getAllMessages();
    }
}
