package com.demo.socialwebapplication.controller;

import com.demo.socialwebapplication.model.MessageDto;
import com.demo.socialwebapplication.model.UserDto;
import com.demo.socialwebapplication.service.CrudService;
import io.swagger.annotations.ApiModel;
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
@ApiModel("Social Web Messaging Application Controller")
public class SocialWebApplicationController {

    @Autowired
    private CrudService service;

    @PostMapping(path = "/postMessage/{userName}")
    public void postJpaMessage(@PathVariable String userName, @RequestBody MessageDto messagePosted) {
        UserDto user = service.getOrCreateUserByUserName(userName);
        service.saveUserMessage(user, messagePosted);
    }

    @GetMapping(path = "/getMessagesByUserName/{userName}")
    public List<MessageDto> getMessagesByUserName(@PathVariable String userName) {
        return service.getMessagesByUserName(userName);
    }

    @GetMapping(path = "/getMessagesFromFollowing/{userName}")
    public List<MessageDto> getMessagesFromFollowing(@PathVariable String userName) {
        return service.getMessagesFromFollowing(userName);
    }

    @PostMapping(path = "/addFollowing/{userName}/{followingUserName}")
    public void addFollower(@PathVariable String userName, @PathVariable String followingUserName) {
        service.addFollower(userName, followingUserName);
    }

    @GetMapping(path = "/getAvailableUsersToFollowForUserName/{userName}")
    public Set<UserDto> getAvailableUsersToFollowForUserName(@PathVariable String userName) {
        return service.getAvailableUsersToFollowForUserName(userName);
    }

    @GetMapping(path = "/checkForValidUserName/{userName}")
    public Boolean checkForValidUserName(@PathVariable String userName) {
        return service.checkForValidUserName(userName);
    }

    @GetMapping(path = "/getAllMessages")
    public List<MessageDto> getAllJpaMessages() {
        return service.getAllMessages();
    }


}
