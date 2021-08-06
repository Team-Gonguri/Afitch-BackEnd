package com.project.exercise.service;

import com.project.auth.exceptions.UserNotExistsException;
import com.project.auth.model.entity.User;
import com.project.auth.model.repository.UserRepository;
import com.project.configs.S3Manager;
import com.project.exception.NotYourContentsException;
import com.project.exercise.exceptions.ExerciseCommentNotExistsException;
import com.project.exercise.exceptions.ExerciseNotExistsException;
import com.project.exercise.exceptions.ExerciseUserNotExistsException;
import com.project.exercise.model.dto.ExerciseUserDto;
import com.project.exercise.model.dto.SimpleExerciseUserDto;
import com.project.exercise.model.entity.Exercise;
import com.project.exercise.model.entity.ExerciseUser;
import com.project.exercise.model.entity.enums.OrderType;
import com.project.exercise.model.entity.enums.PublicScope;
import com.project.exercise.model.repository.ExerciseCommentRepository;
import com.project.exercise.model.repository.ExerciseRepository;
import com.project.exercise.model.repository.ExerciseUserRepository;
import com.project.utils.ConnectorUtils;
import com.project.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseUserService {

    @Value("${application.vision.server}")
    private String visionServerURL;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseUserRepository exerciseUserRepository;
    private final ExerciseCommentRepository exerciseCommentRepository;
    private final UserRepository userRepository;
    private final S3Manager s3Manager;
    private final ConnectorUtils connectorUtils;

    @Transactional
    public ExerciseUserDto getDetailExerciseUser(Long userId, Long exerciseUserId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        ExerciseUser exerciseUser = exerciseUserRepository.findById(exerciseUserId).orElseThrow(ExerciseUserNotExistsException::new);

        if (!exerciseUser.getUser().equals(user) && exerciseUser.getScope().equals(PublicScope.PRIVATE))
            throw new NotYourContentsException();

        return new ExerciseUserDto(exerciseUser);
    }

    @Transactional(readOnly = true)
    public List<SimpleExerciseUserDto> getExerciseUserList(Long exerciseId, OrderType type) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(ExerciseNotExistsException::new);
        List<ExerciseUser> exerciseUsers;
        if (type.equals(OrderType.LATEST))
            exerciseUsers = exerciseUserRepository.findAllByExerciseOrderByIdDesc(exercise);
        else
            exerciseUsers = exerciseUserRepository.findAllByExerciseOrderByScoreDesc(exercise);

        return exerciseUsers.stream().map(SimpleExerciseUserDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void deleteExerciseUserVideo(Long userId, Long exerciseUserId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        ExerciseUser exerciseUser = exerciseUserRepository.findById(exerciseUserId).orElseThrow(ExerciseCommentNotExistsException::new);

        if (!exerciseUser.getUser().equals(user))
            throw new NotYourContentsException();
        s3Manager.deleteFile(exerciseUser.getUrl());
        exerciseUserRepository.delete(exerciseUser);
    }

    @Transactional(rollbackFor = {Exception.class})
    public ExerciseUserDto saveExerciseUserVideo(Long userId, Long exerciseId, MultipartFile video, String open) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(ExerciseNotExistsException::new);
        String url = s3Manager.uploadFile(video);
        ExerciseUser exerciseUser = exerciseUserRepository.findByExerciseAndUserAndCreatedAt(exercise, user, DateUtils.now()).orElseGet(() -> exerciseUserRepository.save(new ExerciseUser(url, PublicScope.valueOf(open), exercise, user)));
        connectorUtils.send(HttpMethod.POST, visionServerURL, url, String.class, Integer.class)
                .subscribe(score -> updateScoreAsynchronous(score, url, exerciseUser));

        return new ExerciseUserDto(exerciseUser, url);
    }

    private void updateScoreAsynchronous(int score, String url, ExerciseUser exerciseUser) {
        if (score > exerciseUser.getScore()) {
            s3Manager.deleteFile(exerciseUser.getUrl());
            exerciseUser.updateScore(score);
            exerciseUser.updateUrl(url);
            exerciseUserRepository.save(exerciseUser);
        } else
            s3Manager.deleteFile(url);
    }
}
