package com.project.auth.model.repository;

import com.project.auth.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountId(String accountId);

    boolean existsByAccountId(String accountId);

    boolean existsByNickName(String nickName);
}
