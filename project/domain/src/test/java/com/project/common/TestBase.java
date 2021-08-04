package com.project.common;

import com.project.common.factory.UserFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestBase {

    protected UserFactory userFactory = new UserFactory();

}
