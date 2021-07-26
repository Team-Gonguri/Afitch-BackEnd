package com.editor.factory;

import com.editor.model.entity.Account;
import com.editor.security.enums.UserRole;

import java.util.Optional;

public class AuthFactory {
    public Account sampleAccount = new Account(1L,
            "ktj7916",
            "encodedPassword",
            "ktj7916@naver.com",
            UserRole.ROLE_USER);

    public Optional<Account> sampleOptionalAccount = Optional.of(new Account(1L,
            "ktj7916",
            "encodedPassword",
            "ktj7916@naver.com",
            UserRole.ROLE_USER));
}
