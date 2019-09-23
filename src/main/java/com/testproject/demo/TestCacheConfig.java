package com.testproject.demo;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestCacheConfig {

    @Autowired
    private CacheManager cacheManager;

    @Bean(name = "lru_cache_service")
    TestCacheService getLruCacheService() {
        return new TestCacheService(TestCacheStrategy.LRU, cacheManager);
    }

    @Bean(name = "lfu_cache_service")
    TestCacheService getLfuCacheService() {
        return new TestCacheService(TestCacheStrategy.LFU, cacheManager);
    }
}
