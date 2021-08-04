package com.project.user.test;

import com.project.auth.exceptions.NotPositiveNumberException;
import com.project.auth.exceptions.UserNotExistsException;
import com.project.auth.model.dto.UserInfoDto;
import com.project.user.UserTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UpdateMyInfoTest extends UserTestBase {

    @Test
    @DisplayName("유저 정보 업데이트 성공")
    public void updateMyInfoSuccess() {
        Mockito.doReturn(userFactory.sampleOptionalUser)
                .when(userRepository).findById(Mockito.anyLong());

        UserInfoDto userInfoDto = new UserInfoDto(userFactory.sampleUser);
        UserInfoDto updateUserInfoDto
                = Assertions.assertDoesNotThrow(() -> userService.updateUserInfo(1L, userInfoDto));

        Assertions.assertEquals(userInfoDto.getNickName(), updateUserInfoDto.getNickName());
        Assertions.assertEquals(userInfoDto.getWeight(), updateUserInfoDto.getWeight());
        Assertions.assertEquals(userInfoDto.getHeight(), updateUserInfoDto.getHeight());
    }

    @Test
    @DisplayName("유저 정보 업데이트 실패(존재하지 않는 유저)")
    public void updateMyInfoFailedBecauseUserNotExist() {
        Mockito.doReturn(Optional.empty())
                .when(userRepository).findById(Mockito.anyLong());

        UserInfoDto userInfoDto = new UserInfoDto(userFactory.sampleUser);

        Assertions.assertThrows(UserNotExistsException.class, () -> userService.updateUserInfo(1L, userInfoDto));
    }

    @Test
    @DisplayName("유저 정보 업데이트 실패(키나 몸무게가 양수가 아님)")
    public void updateMyInfoFailedBecauseHeightOrWeightIsNotPositive() {
        Mockito.doReturn(userFactory.sampleOptionalUser)
                .when(userRepository).findById(Mockito.anyLong());

        UserInfoDto userInfoDto = new UserInfoDto(
                "ktj7916",
                -1,
                -1
        );
        Assertions.assertThrows(NotPositiveNumberException.class, () -> userService.updateUserInfo(1L, userInfoDto));
    }
}
