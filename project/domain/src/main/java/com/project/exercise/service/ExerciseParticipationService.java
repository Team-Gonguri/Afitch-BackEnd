package com.project.exercise.service;

import com.project.auth.exceptions.UserNotExistsException;
import com.project.auth.model.entity.User;
import com.project.auth.model.repository.UserRepository;
import com.project.configs.S3Manager;
import com.project.exception.NotYourContentsException;
import com.project.exercise.exceptions.ExerciseCommentNotExistsException;
import com.project.exercise.exceptions.ExerciseNotExistsException;
import com.project.exercise.exceptions.ExerciseUserNotExistsException;
import com.project.exercise.model.dto.DetailExerciseParticipationDto;
import com.project.exercise.model.dto.SimpleExerciseParticipationDto;
import com.project.exercise.model.dto.vision.VectorDto;
import com.project.exercise.model.dto.vision.VisionBodyDto;
import com.project.exercise.model.entity.Exercise;
import com.project.exercise.model.entity.ExerciseComment;
import com.project.exercise.model.entity.ExerciseParticipation;
import com.project.exercise.model.entity.enums.OrderType;
import com.project.exercise.model.entity.enums.PublicScope;
import com.project.exercise.model.repository.ExerciseCommentRepository;
import com.project.exercise.model.repository.ExerciseParticipationRepository;
import com.project.exercise.model.repository.ExerciseRepository;
import com.project.utils.ConnectorUtils;
import com.project.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseParticipationService {

    @Value("${application.vision.server}")
    private String visionServerURL;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseParticipationRepository exerciseParticipationRepository;
    private final ExerciseCommentRepository exerciseCommentRepository;
    private final UserRepository userRepository;
    private final S3Manager s3Manager;
    private final ConnectorUtils connectorUtils;
    private final VisionService visionService;

    @Transactional
    public DetailExerciseParticipationDto getDetailExerciseUser(Long userId, Long exerciseUserId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        ExerciseParticipation exerciseParticipation = exerciseParticipationRepository.findById(exerciseUserId).orElseThrow(ExerciseUserNotExistsException::new);

        if (!exerciseParticipation.getUser().equals(user) && exerciseParticipation.getScope().equals(PublicScope.PRIVATE))
            throw new NotYourContentsException();

        return new DetailExerciseParticipationDto(exerciseParticipation);
    }

    @Transactional(readOnly = true)
    public List<SimpleExerciseParticipationDto> getExerciseUserList(Long exerciseId, OrderType type) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(ExerciseNotExistsException::new);
        List<ExerciseParticipation> exerciseParticipations;
        if (type.equals(OrderType.LATEST))
            exerciseParticipations = exerciseParticipationRepository.findAllByExerciseOrderByIdDesc(exercise);
        else
            exerciseParticipations = exerciseParticipationRepository.findAllByExerciseOrderByScoreDesc(exercise);

        return exerciseParticipations.stream().map(SimpleExerciseParticipationDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void deleteExerciseUserVideo(Long userId, Long exerciseUserId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        ExerciseParticipation exerciseParticipation = exerciseParticipationRepository.findById(exerciseUserId).orElseThrow(ExerciseCommentNotExistsException::new);
        List<ExerciseComment> comments = exerciseCommentRepository.findAllByExerciseParticipation(exerciseParticipation);
        if (!exerciseParticipation.getUser().equals(user))
            throw new NotYourContentsException();
        s3Manager.deleteFile(exerciseParticipation.getUrl());
        exerciseCommentRepository.deleteInBatch(comments);
        exerciseParticipationRepository.delete(exerciseParticipation);
    }

    @Transactional
    public List<SimpleExerciseParticipationDto> getTodayTop4Participation(Long userId) throws ParseException {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        return exerciseParticipationRepository.findTop4ByUserAndCreatedAtOrderByScoreDesc(user, DateUtils.now()).stream().map(SimpleExerciseParticipationDto::new).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = {Exception.class})
    public DetailExerciseParticipationDto saveExerciseUserVideo(Long userId, Long exerciseId, MultipartFile video, String open) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(ExerciseNotExistsException::new);
        String url = s3Manager.uploadFile(video);
        ExerciseParticipation exerciseParticipation = exerciseParticipationRepository.findByExerciseAndUserAndCreatedAt(exercise, user, DateUtils.now()).orElseGet(() -> exerciseParticipationRepository.save(new ExerciseParticipation(url, PublicScope.valueOf(open), exercise, user)));
        connectorUtils.send(HttpMethod.POST, visionServerURL, new VisionBodyDto(visionService.getPoseData(exercise.getUrl())), VisionBodyDto.class, Double.class)
                .subscribe(score -> update(score, url, exerciseParticipation));

        return new DetailExerciseParticipationDto(exerciseParticipation, url);
    }

    @Transactional
    public void update(double score, String url, ExerciseParticipation exerciseParticipation) {
        if (score >= exerciseParticipation.getScore()){
            s3Manager.deleteFile(exerciseParticipation.getUrl());
            exerciseParticipation.updateUrl(url);
            exerciseParticipation.updateScore(score);
        }else
            s3Manager.deleteFile(url);
    }
}
