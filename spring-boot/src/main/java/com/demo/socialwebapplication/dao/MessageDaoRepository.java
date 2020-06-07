package com.demo.socialwebapplication.dao;

import com.demo.socialwebapplication.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDaoRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAllByUserIdIn(List<Long> userIds);

    List<MessageEntity> findAllByUserId(Long userIds);
}
