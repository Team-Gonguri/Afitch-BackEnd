package com.project.exercise.service;

import com.project.DateUtils;
import com.project.auth.exceptions.UserNotExistsException;
import com.project.auth.model.entity.User;
import com.project.auth.model.repository.UserRepository;
import com.project.configs.S3Manager;
import com.project.diet.exceptions.ExerciseNotExistsException;
import com.project.exercise.model.entity.Exercise;
import com.project.exercise.model.entity.ExerciseUser;
import com.project.exercise.model.entity.dto.ExpertExerciseDto;
import com.project.exercise.model.entity.dto.UserExerciseDto;
import com.project.exercise.model.entity.enums.PublicScope;
import com.project.exercise.model.repository.ExerciseRepository;
import com.project.exercise.model.repository.ExerciseUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseUserRepository exerciseUserRepository;
    private final UserRepository userRepository;
    private final S3Manager s3Manager;

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

    @Transactional
    public UserExerciseDto saveUserExerciseVideo(Long userId, Long exerciseId, MultipartFile video, String open) throws ParseException {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(ExerciseNotExistsException::new);
        String url = s3Manager.uploadFile(video);
        ExerciseUser exerciseUser = exerciseUserRepository.save(
                new ExerciseUser(
                        null,
                        url,
                        PublicScope.valueOf(open),
                        -1,
                        exercise,
                        user,
                        DateUtils.now()
                )
        );

        return new UserExerciseDto(exerciseUser);
    }


}
