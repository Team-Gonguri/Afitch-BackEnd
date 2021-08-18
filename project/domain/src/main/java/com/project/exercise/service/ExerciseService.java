package com.project.exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.configs.S3Manager;
import com.project.exception.UnExpectedError;
import com.project.exercise.exceptions.ExerciseAlreadyExistsException;
import com.project.exercise.exceptions.ExerciseNotExistsException;
import com.project.exercise.model.dto.DetailExerciseDto;
import com.project.exercise.model.dto.SimpleExerciseDto;
import com.project.exercise.model.dto.vision.PoseDataDto;
import com.project.exercise.model.entity.Exercise;
import com.project.exercise.model.entity.enums.ExerciseCategory;
import com.project.exercise.model.repository.ExerciseRepository;
import com.project.utils.ConnectorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    @Value("${application.vision.server}")
    private String visionServerURL;
    private final S3Manager s3Manager;
    private final ExerciseRepository exerciseRepository;
    private final ConnectorUtils connectorUtils;
    private final VisionService visionService;


    @Transactional
    @CacheEvict(cacheNames = "exercise_list", key = "#category")
    public DetailExerciseDto saveExercise(String name, ExerciseCategory category, MultipartFile video) throws IOException {
        if (exerciseRepository.existsByName(name))
            throw new ExerciseAlreadyExistsException();

        String url = s3Manager.uploadFile(video);
        Exercise exercise = exerciseRepository.save(new Exercise(null, name, url, category));
        connectorUtils.send(HttpMethod.GET, visionServerURL + "/vector?url=" + url,PoseDataDto[].class)
                .subscribe(data -> {
                    try {
                        visionService.save(url,data);
                    } catch (JsonProcessingException e) {
                        throw new UnExpectedError("JsonProcessing Error");
                    }
                });
        return new DetailExerciseDto(exercise);
    }

    public List<String> getExerciseCategory() {
        return Arrays.stream(ExerciseCategory.class.getFields()).map(Field::getName).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "exercise_list", key = "#category")
    public List<SimpleExerciseDto> getExercises(ExerciseCategory category) {
        return exerciseRepository.findAllByCategory(category).stream().map(SimpleExerciseDto::new).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public DetailExerciseDto getExpertExerciseVideo(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(ExerciseNotExistsException::new);
        return new DetailExerciseDto(exercise);
    }
}
