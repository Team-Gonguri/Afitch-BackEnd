package com.project.user;


import com.project.auth.model.repository.UserRepository;
import com.project.auth.service.UserService;
import com.project.common.TestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserTestBase extends TestBase {

    @Mock
    protected UserRepository userRepository;

    @InjectMocks
    protected UserService userService;

}
