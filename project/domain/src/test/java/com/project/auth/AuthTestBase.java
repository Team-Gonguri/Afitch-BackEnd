package com.project.auth;

import com.project.auth.model.repository.UserRepository;
import com.project.auth.service.AuthService;
import com.project.common.TestBase;
import com.project.common.factory.UserFactory;
import com.project.security.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

public class AuthTestBase extends TestBase {
    @Mock
    protected PasswordEncoder passwordEncoder;

    @Mock
    protected UserRepository userRepository;

    @Spy
    protected JwtProvider jwtProvider = new JwtProvider();

    @InjectMocks
    protected AuthService authService;

    @BeforeEach
    public void setJwtProvider() {
        ReflectionTestUtils.setField(jwtProvider, "secret", "TEST_JWT_SECRET");
        ReflectionTestUtils.setField(jwtProvider, "accessValidTime", 86400000L);
        ReflectionTestUtils.setField(jwtProvider, "refreshValidTime", 604800000L);
    }
}
