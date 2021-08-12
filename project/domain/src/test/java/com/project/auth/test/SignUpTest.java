package com.project.auth.test;

import com.project.auth.AuthTestBase;
import com.project.auth.model.dto.SignUpDto;
import com.project.auth.model.dto.TokenDto;
import com.project.security.enums.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SignUpTest extends AuthTestBase {

    @Test
    @DisplayName("회원가입 성공")
    public void singUpSuccess() {

        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(userFactory.sampleUser);

        SignUpDto signUpDto = new SignUpDto("ktj7916", "1q2w3e4r!!", "ktj7916@naver.com", UserRole.ROLE_USER, "");
        TokenDto tokenDto = Assertions.assertDoesNotThrow(() -> authService.signUp(signUpDto));

        Assertions.assertNotNull(tokenDto);
        Assertions.assertNotNull(tokenDto.getAccess());
        Assertions.assertNotNull(tokenDto.getRefresh());
    }
}
