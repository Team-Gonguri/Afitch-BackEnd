package com.project.auth.service;

import com.project.auth.exceptions.NickNameAlreadyExistException;
import com.project.auth.exceptions.NotPositiveNumberException;
import com.project.auth.exceptions.UserNotExistsException;
import com.project.auth.model.dto.UserInfoDto;
import com.project.auth.model.entity.User;
import com.project.auth.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserInfoDto getMyInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        return new UserInfoDto(user);
    }

    @Transactional
    public UserInfoDto updateUserInfo(Long userId, UserInfoDto dto) {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);

        if (dto.getHeight() <= 0 || dto.getWeight() <= 0)
            throw new NotPositiveNumberException();
        if (!dto.getNickName().equals(user.getNickName()) && userRepository.existsByNickName(dto.getNickName()))
            throw new NickNameAlreadyExistException();

        user.updateInfo(dto);
        return new UserInfoDto(user);
    }
}
