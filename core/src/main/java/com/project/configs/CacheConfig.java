package com.project.configs;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CacheConfig {
    @Bean
    CaffeineCache caffeineCache() {
        return new CaffeineCache(
                "food_categories",
                Caffeine.newBuilder()
                        .expireAfterWrite(Duration.ofDays(30))
                        .maximumSize(20)
                        .build());
    }
}
