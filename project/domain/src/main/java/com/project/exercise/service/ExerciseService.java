package com.project.exercise.service;

import com.project.auth.exceptions.UserNotExistsException;
import com.project.auth.model.entity.User;
import com.project.auth.model.repository.UserRepository;
import com.project.configs.S3Manager;
import com.project.diet.exceptions.ExerciseNotExistsException;
import com.project.exercise.model.entity.Exercise;
import com.project.exercise.model.entity.ExerciseUser;
import com.project.exercise.model.dto.ExpertExerciseDto;
import com.project.exercise.model.dto.UserExerciseDto;
import com.project.exercise.model.entity.enums.PublicScope;
import com.project.exercise.model.repository.ExerciseRepository;
import com.project.exercise.model.repository.ExerciseUserRepository;
import com.project.utils.ConnectorUtils;
import com.project.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseUserRepository exerciseUserRepository;
    private final UserRepository userRepository;
    private final S3Manager s3Manager;
    private final ConnectorUtils connectorUtils;

    @Value("${application.vision.server}")
    private String visionServerURL;

    @Transactional(readOnly = true)
    public ExpertExerciseDto getExpertExerciseVideo(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(ExerciseNotExistsException::new);
        return new ExpertExerciseDto(
                exercise.getId(),
                exercise.getName(),
                exercise.getType(),
                exercise.getUrl()
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public UserExerciseDto saveUserExerciseVideo(Long userId, Long exerciseId, MultipartFile video, String open) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(ExerciseNotExistsException::new);
        String url = s3Manager.uploadFile(video);
        ExerciseUser exerciseUser = exerciseUserRepository.findByExerciseAndUserAndCreatedAt(exercise, user, DateUtils.now()).orElseGet(() -> exerciseUserRepository.save(new ExerciseUser(url, PublicScope.valueOf(open), exercise, user)));
        connectorUtils.send(HttpMethod.POST, visionServerURL, url, String.class, Integer.class)
                .subscribe(score -> update(score, url, exerciseUser));

        return new UserExerciseDto(exerciseUser, url);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    protected void update(int score, String url, ExerciseUser exerciseUser) {
        if (score > exerciseUser.getScore()) {
            s3Manager.deleteFile(exerciseUser.getUrl());
            exerciseUser.updateScore(score);
            exerciseUser.updateUrl(url);
            exerciseUserRepository.save(exerciseUser);
        } else
            s3Manager.deleteFile(url);
    }
}
