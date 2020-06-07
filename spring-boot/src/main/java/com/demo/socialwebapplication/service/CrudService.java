package com.demo.socialwebapplication.service;

import com.demo.socialwebapplication.dao.MessageDaoRepository;
import com.demo.socialwebapplication.dao.UserDaoRepository;
import com.demo.socialwebapplication.dao.UserFollowDaoRepository;
import com.demo.socialwebapplication.entities.MessageEntity;
import com.demo.socialwebapplication.entities.UserEntity;
import com.demo.socialwebapplication.entities.UserFollowEntity;
import com.demo.socialwebapplication.model.MessageDto;
import com.demo.socialwebapplication.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.demo.socialwebapplication.util.EntityDtoMapper.mapMessageDtoToEntity;
import static com.demo.socialwebapplication.util.EntityDtoMapper.mapMessageEntitiesToDtos;
import static com.demo.socialwebapplication.util.EntityDtoMapper.mapUserEntitiesToDtos;
import static com.demo.socialwebapplication.util.EntityDtoMapper.mapUserEntityToDto;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.toList;

@Service
public class CrudService {


    @Autowired
    private UserDaoRepository userDaoRepository;

    @Autowired
    private MessageDaoRepository messageDaoRepository;

    @Autowired
    private UserFollowDaoRepository userFollowDaoRepository;

    public List<MessageDto> getAllMessages() {
        List<MessageEntity> entities = messageDaoRepository.findAll();
        List<MessageDto> dtos = mapMessageEntitiesToDtos(entities);
        return dtos;
    }

    public UserDto getOrCreateUserByUserName(String userName) {
        Optional<UserEntity> userEntity = userDaoRepository.findByUserName(userName);

        if (userEntity.isPresent()) {
            return mapUserEntityToDto(userEntity.get());
        }
        UserEntity entity = new UserEntity();
        entity.setUserName(userName);
        entity = userDaoRepository.save(entity);

        return mapUserEntityToDto(entity);
    }

    public void saveUserMessage(UserDto user, MessageDto messagePosted) {
        MessageEntity entity = mapMessageDtoToEntity(messagePosted);
        entity.setUserId(user.getUserId());
        entity.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        messageDaoRepository.save(entity);
    }

    public List<MessageDto> getMessagesByUserName(String userName) {
        Optional<UserEntity> userEntity = userDaoRepository.findByUserName(userName);
        List<MessageDto> messageDtos = newArrayList();
        if (userEntity.isPresent()) {
            List<MessageEntity> messageEntities = messageDaoRepository.findAllByUserId(userEntity.get().getUserId());
            messageDtos = mapMessageEntitiesToDtos(messageEntities);
        }
        return messageDtos;
    }

    public boolean checkForValidUserName(String userName) {
        Optional<UserEntity> entity = userDaoRepository.findByUserName(userName);
        return entity.isPresent();
    }

    public void addFollower(String userName, String followingUserName) {
        Optional<UserEntity> userEntity = userDaoRepository.findByUserName(userName);
        Optional<UserEntity> followedUserEntity = userDaoRepository.findByUserName(followingUserName);
        if (userEntity.isPresent() && followedUserEntity.isPresent()) {
            UserFollowEntity userFollowEntity = new UserFollowEntity();
            userFollowEntity.setUserId(userEntity.get().getUserId());
            userFollowEntity.setFollowingUserId(followedUserEntity.get().getUserId());
            userFollowDaoRepository.save(userFollowEntity);
        }

    }

    public List<MessageDto> getMessagesFromFollowing(String userName) {
        Optional<UserEntity> userEntity = userDaoRepository.findByUserName(userName);
        if (userEntity.isPresent()) {
            List<UserFollowEntity> userFollowEntities = userFollowDaoRepository.findAllByUserId(userEntity.get().getUserId());
            List<Long> userFollowedIds = userFollowEntities.stream().map(t -> t.getFollowingUserId()).collect(toList());
            List<MessageEntity> messageEntities = messageDaoRepository.findAllByUserIdIn(userFollowedIds);
            List<UserEntity> userEntities = userDaoRepository.findAllByUserIdIn(userFollowedIds);
            Map<Long, String> userIdToUserName = userEntities.stream().collect(Collectors.toMap(x -> x.getUserId(), x -> x.getUserName()));
            return mapMessageEntitiesToDtos(messageEntities, userIdToUserName);
        }
        return newArrayList();
    }

    public Set<UserDto> getAvailableUsersToFollowForUserName(String userName) {
        Optional<UserEntity> userEntity = userDaoRepository.findByUserName(userName);
        if (userEntity.isPresent()) {
            List<UserEntity> allUserEntities = userDaoRepository.findAllByUserIdNot(userEntity.get().getUserId());
            List<UserFollowEntity> userFollowEntities = userFollowDaoRepository.findAllByUserId(userEntity.get().getUserId());
            List<Long> allUserIds = allUserEntities.stream().map(t -> t.getUserId()).collect(toList());
            List<Long> userFollowedIds = userFollowEntities.stream().map(t -> t.getFollowingUserId()).collect(toList());
            allUserIds.removeAll(userFollowedIds);
            List<UserEntity> availableUserEntitiesToFollow = userDaoRepository.findAllByUserIdIn(allUserIds);
            return newHashSet(mapUserEntitiesToDtos(availableUserEntitiesToFollow));
        }
        return newHashSet();
    }
}
