package com.testproject.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestCacheConfig {

    @Bean(name = "lru_cache_service")
    TestCacheService getLruCacheService() {
        return new TestCacheService(3, new LruEvictionStrategy());
    }

    @Bean(name = "lfu_cache_service")
    TestCacheService getLfuCacheService() {
        return new TestCacheService(3, new LfuEvictionStrategy());
    }
}
