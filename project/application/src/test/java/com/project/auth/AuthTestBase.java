package com.project.auth;

import com.project.common.CommonTestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthTestBase extends CommonTestBase {
    @Autowired
    protected PasswordEncoder passwordEncoder;
}
