package com.project.auth.test;

import com.project.auth.AuthTestBase;
import com.project.auth.exceptions.WrongAccountInfoException;
import com.project.auth.model.dto.SignInDto;
import com.project.auth.model.dto.TokenDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class SignInTest extends AuthTestBase {

    @Test
    @DisplayName("로그인 성공")
    public void signInSuccess() {
        Mockito.when(userRepository.findByAccountId(Mockito.anyString()))
                .thenReturn(userFactory.sampleOptionalUser);

        Mockito.when(passwordEncoder.matches(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(true);

        SignInDto signInDto = new SignInDto("ktj7916", "rawPassword");
        TokenDto tokenDto = Assertions.assertDoesNotThrow(() -> authService.signIn(signInDto));
        Assertions.assertNotNull(tokenDto);
        Assertions.assertNotNull(tokenDto.getAccess());
        Assertions.assertNotNull(tokenDto.getRefresh());
    }

    @Test
    @DisplayName("로그인 실패(존재하지 않는 ID)")
    public void testFailedBecauseOfWrongId() {
        Mockito.when(userRepository.findByAccountId(Mockito.anyString()))
                .thenReturn(Optional.empty());

        SignInDto signInDto = new SignInDto("WrongUserId", "rawPassword");
        Assertions.assertThrows(WrongAccountInfoException.class, () -> authService.signIn(signInDto));
    }

    @Test
    @DisplayName("로그인 실패(잘못된 패스워드)")
    public void testFailedBecauseOfWrongPassword(){
        Mockito.when(userRepository.findByAccountId(Mockito.anyString()))
                .thenReturn(userFactory.sampleOptionalUser);
        Mockito.when(passwordEncoder.matches(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(false);

        SignInDto signInDto = new SignInDto("WrongUserId", "rawPassword");
        Assertions.assertThrows(WrongAccountInfoException.class, () -> authService.signIn(signInDto));

    }
}
