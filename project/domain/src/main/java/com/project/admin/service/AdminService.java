package com.project.admin.service;

import com.project.configs.S3Manager;
import com.project.exercise.model.dto.DetailExerciseDto;
import com.project.exercise.model.entity.Exercise;
import com.project.exercise.model.entity.enums.ExerciseCategory;
import com.project.exercise.model.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final S3Manager s3Manager;
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public DetailExerciseDto saveExercise(String name, ExerciseCategory category, MultipartFile video) throws IOException {
        String url = s3Manager.uploadFile(video);
        Exercise exercise = exerciseRepository.save(new Exercise(
                null, name, url, category
        ));
        return new DetailExerciseDto(exercise);
    }
}
