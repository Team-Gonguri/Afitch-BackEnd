package com.project.auth;

import com.project.AuthTestBase;
import com.project.auth.model.dto.SignUpDto;
import com.project.auth.model.dto.TokenDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SignUpTest extends AuthTestBase {

    @Test
    @DisplayName("회원가입 성공")
    public void singUpSuccess() {

        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(authFactory.sampleUser);

        SignUpDto signUpDto = new SignUpDto("ktj7916", "1q2w3e4r!!", "ktj7916@naver.com");
        TokenDto tokenDto = Assertions.assertDoesNotThrow(() -> authService.signUp(signUpDto));

        Assertions.assertNotNull(tokenDto);
        Assertions.assertNotNull(tokenDto.getAccessToken());
        Assertions.assertNotNull(tokenDto.getRefreshToken());
    }
}
