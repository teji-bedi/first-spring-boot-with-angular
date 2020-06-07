package com.demo.socialwebapplication.dao;

import com.demo.socialwebapplication.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDaoRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);

    List<UserEntity> findAllByUserIdNot(Long userId);

    List<UserEntity> findAllByUserIdIn(List<Long> allUserIds);
}
