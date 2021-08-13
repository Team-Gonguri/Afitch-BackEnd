package com.project.exercise.service;

import com.project.exercise.model.dto.VectorDto;
import com.project.exercise.model.entity.ExerciseParticipation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class VisionService {

    private final RedisTemplate redisTemplate;


    public int calculate(VectorDto[] vectors, String url, ExerciseParticipation exerciseParticipation) {
        Arrays.stream(vectors).forEach(System.out::println);
        return 1;
    }
}
