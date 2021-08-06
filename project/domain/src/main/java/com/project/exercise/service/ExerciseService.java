package com.project.exercise.service;

import com.project.exercise.exceptions.ExerciseNotExistsException;
import com.project.exercise.model.dto.DetailExerciseDto;
import com.project.exercise.model.dto.SimpleExerciseDto;
import com.project.exercise.model.entity.Exercise;
import com.project.exercise.model.entity.enums.ExerciseCategory;
import com.project.exercise.model.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;


    public List<String> getExerciseCategory(){
        return Arrays.stream(ExerciseCategory.class.getFields()).map(Field::getName).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "exercise_list",key = "#category")
    public List<SimpleExerciseDto> getExercises(ExerciseCategory category){
        return exerciseRepository.findAllByType(category).stream().map(SimpleExerciseDto::new).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public DetailExerciseDto getExpertExerciseVideo(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(ExerciseNotExistsException::new);
        return new DetailExerciseDto(exercise);
    }
}
