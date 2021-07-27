package com.project;

import com.project.factory.AuthFactory;
import com.project.auth.model.repository.UserRepository;
import com.project.security.JwtProvider;
import com.project.auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class AuthTestBase {
    @Mock
    protected PasswordEncoder passwordEncoder;

    @Mock
    protected UserRepository userRepository;

    @Spy
    protected JwtProvider jwtProvider = new JwtProvider();

    @InjectMocks
    protected AuthService authService;

    protected AuthFactory authFactory = new AuthFactory();

    @BeforeEach
    public void setJwtProvider() {
        ReflectionTestUtils.setField(jwtProvider, "secret", "TEST_JWT_SECRET");
        ReflectionTestUtils.setField(jwtProvider, "accessValidTime", 86400000L);
        ReflectionTestUtils.setField(jwtProvider, "refreshValidTime", 604800000L);
    }
}
