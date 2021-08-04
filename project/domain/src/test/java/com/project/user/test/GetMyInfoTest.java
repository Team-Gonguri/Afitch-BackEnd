package com.project.user.test;

import com.project.auth.exceptions.UserNotExistsException;
import com.project.auth.model.dto.UserInfoDto;
import com.project.user.UserTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class GetMyInfoTest extends UserTestBase {

    @Test
    @DisplayName("내 정보 가져오기 테스트")
    public void getMyInfoSuccess() {
        Mockito.doReturn(userFactory.sampleOptionalUser)
                .when(userRepository).findById(Mockito.anyLong());

        UserInfoDto userInfoDto = Assertions.assertDoesNotThrow(() -> userService.getMyInfo(1L));
        Assertions.assertEquals(userInfoDto.getHeight(), 170.0);
        Assertions.assertEquals(userInfoDto.getWeight(), 57.0);
        Assertions.assertEquals(userInfoDto.getNickName(), userFactory.sampleUser.getNickName());
    }

    @Test
    @DisplayName("내 정보 가져오기 테스트 실패(존재 하지않는 유저)")
    public void getMyInfoFailedBecauseUserNotExist() {
        Mockito.doReturn(Optional.empty())
                .when(userRepository).findById(Mockito.anyLong());

        Assertions.assertThrows(UserNotExistsException.class, () -> userService.getMyInfo(1L));
    }
}
