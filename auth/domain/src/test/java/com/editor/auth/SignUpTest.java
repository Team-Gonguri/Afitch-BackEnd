package com.editor.auth;

import com.editor.AuthTestBase;
import com.editor.model.dto.SignUpDto;
import com.editor.model.dto.TokenDto;
import com.editor.model.entity.Account;
import com.editor.security.enums.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SignUpTest extends AuthTestBase {

    @Test
    @DisplayName("회원가입 성공")
    public void singUpSuccess() {

        Mockito.when(accountRepository.save(Mockito.any()))
                .thenReturn(authFactory.sampleAccount);

        SignUpDto signUpDto = new SignUpDto("ktj7916", "1q2w3e4r!!", "ktj7916@naver.com");
        TokenDto tokenDto = Assertions.assertDoesNotThrow(() -> authService.signUp(signUpDto));

        Assertions.assertNotNull(tokenDto);
        Assertions.assertNotNull(tokenDto.getAccessToken());
        Assertions.assertNotNull(tokenDto.getRefreshToken());
    }
}
