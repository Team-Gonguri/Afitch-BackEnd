package com.project.factory;

import com.project.auth.model.entity.User;
import com.project.security.enums.UserRole;

import java.util.Optional;

public class AuthFactory {
    public User sampleUser = new User(1L,
            "ktj7916",
            "encodedPassword",
            "ktj7916@naver.com",
            UserRole.ROLE_USER);

    public Optional<User> sampleOptionalAccount = Optional.of(new User(1L,
            "ktj7916",
            "encodedPassword",
            "ktj7916@naver.com",
            UserRole.ROLE_USER));
}
