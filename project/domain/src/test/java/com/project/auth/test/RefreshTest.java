package com.project.auth.test;

import com.project.auth.AuthTestBase;
import com.project.auth.exceptions.InvalidRefreshTokenException;
import com.project.security.JwtTokenDto;
import com.project.security.enums.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RefreshTest extends AuthTestBase {

    @Test
    @DisplayName("Refresh 토큰 정상 갱신")
    public void refreshSuccess() {
        /*
            Spy객체 Mocking할 때 when -> thenReturn이 아니라 doReturn -> when 사용
         */
        Mockito.doReturn(true)
                .when(jwtProvider).validateToken(Mockito.anyString());
        Mockito.doReturn(1L)
                .when(jwtProvider).getUserIdFromToken(Mockito.anyString());
        Mockito.doReturn(UserRole.ROLE_USER)
                .when(jwtProvider).getRoleFromToken(Mockito.anyString());

        JwtTokenDto newAccessToken = Assertions.assertDoesNotThrow(() -> authService.refresh("validRefreshToken"));
        Assertions.assertNotNull(newAccessToken);
    }

    @Test
    @DisplayName("갱신 실패(비정상 토큰)")
    public void testFailedBecauseOfInvalidToken() {
        Mockito.doReturn(false)
                .when(jwtProvider).validateToken(Mockito.anyString());
        Assertions.assertThrows(InvalidRefreshTokenException.class, () -> authService.refresh("inValidRefreshToken"));

    }
}
