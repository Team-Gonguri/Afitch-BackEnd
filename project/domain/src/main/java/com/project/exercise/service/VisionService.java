package com.project.exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.exercise.model.dto.vision.PoseDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisionService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public void save(String url, PoseDataDto[] data) throws JsonProcessingException {
        String dataToString = objectMapper.writeValueAsString(data);
        redisTemplate.opsForValue().set(url, dataToString);
    }

    public PoseDataDto[] getPoseData(String url) throws JsonProcessingException {
        String stringData = redisTemplate.opsForValue().get(url);
        return objectMapper.readValue(stringData, PoseDataDto[].class);
    }
}
