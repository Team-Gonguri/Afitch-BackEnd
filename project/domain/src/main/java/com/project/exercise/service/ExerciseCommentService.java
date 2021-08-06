package com.project.exercise.service;

import com.project.auth.exceptions.UserNotExistsException;
import com.project.auth.model.entity.User;
import com.project.auth.model.repository.UserRepository;
import com.project.exception.NotYourContentsException;
import com.project.exercise.exceptions.ExerciseCommentNotExistsException;
import com.project.exercise.exceptions.ExerciseUserNotExistsException;
import com.project.exercise.model.dto.ExerciseCommentDto;
import com.project.exercise.model.entity.ExerciseComment;
import com.project.exercise.model.entity.ExerciseUser;
import com.project.exercise.model.repository.ExerciseCommentRepository;
import com.project.exercise.model.repository.ExerciseUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseCommentService {

    private final UserRepository userRepository;
    private final ExerciseUserRepository exerciseUserRepository;
    private final ExerciseCommentRepository exerciseCommentRepository;

    @Transactional(readOnly = true)
    public List<ExerciseCommentDto> getComments(Long exerciseUserId) {
        ExerciseUser exerciseUser = exerciseUserRepository.findById(exerciseUserId).orElseThrow(ExerciseUserNotExistsException::new);
        return exerciseCommentRepository.findAllByExerciseUser(exerciseUser).stream().map(ExerciseCommentDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ExerciseCommentDto saveComment(Long userId, Long exerciseUserId, String text) {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        ExerciseUser exerciseUser = exerciseUserRepository.findById(exerciseUserId).orElseThrow(ExerciseUserNotExistsException::new);
        ExerciseComment comment = exerciseCommentRepository.save(new ExerciseComment(null, exerciseUser, user, text, new Date()));

        return new ExerciseCommentDto(comment);
    }

    @Transactional
    public ExerciseCommentDto updateComment(Long userId, Long commentId, String text) {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        ExerciseComment comment = exerciseCommentRepository.findById(commentId).orElseThrow(ExerciseCommentNotExistsException::new);
        if (!comment.getUser().equals(user))
            throw new NotYourContentsException();
        comment.updateComment(text);
        return new ExerciseCommentDto(comment);
    }

    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        ExerciseComment comment = exerciseCommentRepository.findById(commentId).orElseThrow(ExerciseCommentNotExistsException::new);

        if (!comment.getUser().equals(user))
            throw new NotYourContentsException();
        exerciseCommentRepository.delete(comment);
    }
}