package com.project.common.factory;

import com.project.auth.model.entity.User;
import com.project.security.enums.UserRole;

import java.util.Optional;

public class UserFactory {
    public User sampleUser = new User(null,
            "ktj7916",
            "encodedPassword",
            "ktj7916@naver.com",
            170.0,
            57.0,
            UserRole.ROLE_USER);

    public Optional<User> sampleOptionalUser = Optional.of(sampleUser);
}
