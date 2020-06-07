package com.demo.socialwebapplication.dao;

import com.demo.socialwebapplication.entities.UserFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowDaoRepository extends JpaRepository<UserFollowEntity, Long> {
    List<UserFollowEntity> findAllByUserId(Long userId);
}
