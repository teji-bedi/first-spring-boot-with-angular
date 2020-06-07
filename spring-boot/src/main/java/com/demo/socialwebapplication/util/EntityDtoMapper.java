package com.demo.socialwebapplication.util;

import com.demo.socialwebapplication.entities.MessageEntity;
import com.demo.socialwebapplication.entities.UserEntity;
import com.demo.socialwebapplication.model.MessageDto;
import com.demo.socialwebapplication.model.UserDto;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.sort;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class EntityDtoMapper {

    public static List<UserDto> mapUserEntitiesToDtos(List<UserEntity> entities) {
        if (isEmpty(entities)) {
            return newArrayList();
        }
        List<UserDto> dtos = newArrayList();
        UserDto dto;
        for (UserEntity entity : entities) {
            dto = new UserDto();
            dto.setUserId(entity.getUserId());
            dto.setUserName(entity.getUserName());
            dtos.add(dto);
        }
        return dtos;
    }

    public static UserDto mapUserEntityToDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setUserId(entity.getUserId());
        dto.setUserName(entity.getUserName());
        return dto;
    }

    public static List<UserEntity> mapUserDtosToEntities(List<UserDto> dtos) {
        if (isEmpty(dtos)) {
            return newArrayList();
        }
        List<UserEntity> entities = newArrayList();
        UserEntity entity;
        for (UserDto dto : dtos) {
            entity = new UserEntity();
            entity.setUserId(dto.getUserId());
            entity.setUserName(dto.getUserName());
            entities.add(entity);
        }
        return entities;
    }

    public static UserEntity mapUserDtoToEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setUserId(dto.getUserId());
        entity.setUserName(dto.getUserName());
        return entity;
    }

//

    public static List<MessageDto> mapMessageEntitiesToDtos(List<MessageEntity> entities, Map<Long, String> userIdToUserName) {
        if (isEmpty(entities)) {
            return newArrayList();
        }
        List<MessageDto> dtos = newArrayList();
        MessageDto dto;
        for (MessageEntity entity : entities) {
            dto = new MessageDto();
            dto.setUserId(entity.getUserId());
            dto.setMessageId(entity.getMessageId());
            dto.setMessage(entity.getMessage());
            dto.setCreatedAt(entity.getCreatedAt());
            dto.setUserName(userIdToUserName.get(entity.getUserId()));
            dtos.add(dto);
        }
        sort(dtos);
        return dtos;
    }

    public static List<MessageDto> mapMessageEntitiesToDtos(List<MessageEntity> entities) {
        if (isEmpty(entities)) {
            return newArrayList();
        }
        List<MessageDto> dtos = newArrayList();
        MessageDto dto;
        for (MessageEntity entity : entities) {
            dto = new MessageDto();
            dto.setUserId(entity.getUserId());
            dto.setMessageId(entity.getMessageId());
            dto.setMessage(entity.getMessage());
            dto.setCreatedAt(entity.getCreatedAt());
            dtos.add(dto);
        }
        sort(dtos);
        return dtos;
    }

    public static MessageDto mapMessageEntityToDto(MessageEntity entity) {
        MessageDto dto = new MessageDto();
        dto.setUserId(entity.getUserId());
        dto.setMessageId(entity.getMessageId());
        dto.setMessage(entity.getMessage());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static List<MessageEntity> mapMessageDtosToEntities(List<MessageDto> dtos) {
        if (isEmpty(dtos)) {
            return newArrayList();
        }
        List<MessageEntity> entities = newArrayList();
        MessageEntity entity;
        for (MessageDto dto : dtos) {
            entity = new MessageEntity();
            entity.setUserId(dto.getUserId());
            entity.setMessageId(dto.getMessageId());
            entity.setMessage(dto.getMessage());
            entity.setCreatedAt(dto.getCreatedAt() == null ? null : new Timestamp(dto.getCreatedAt().getTime()));
            entities.add(entity);
        }
        return entities;
    }

    public static MessageEntity mapMessageDtoToEntity(MessageDto dto) {
        MessageEntity entity = new MessageEntity();
        entity.setUserId(dto.getUserId());
        entity.setMessageId(dto.getMessageId());
        entity.setMessage(dto.getMessage());
        entity.setCreatedAt(dto.getCreatedAt() == null ? null : new Timestamp(dto.getCreatedAt().getTime()));
        return entity;
    }
}
