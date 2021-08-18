package com.project.exercise.service;

import com.project.exercise.model.dto.vision.PoseDataDto;
import com.project.exercise.model.dto.vision.VisionBodyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VisionService {

    private final RedisTemplate<String, PoseDataDto[]> redisTemplate;

    public void save(String url, VisionBodyDto dto) {
        ListOperations<String, PoseDataDto[]> listOperations = redisTemplate.opsForList();
        listOperations.rightPushAll(url, dto.getData());
    }

    public PoseDataDto[] getPoseData(String url) {
        ListOperations<String, PoseDataDto[]> listOperations = redisTemplate.opsForList();
        return listOperations.range(url, 0, -1).stream().toArray(PoseDataDto[]::new);
    }
}
