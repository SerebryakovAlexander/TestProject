package com.testproject.demo;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class TestCacheService {

    private final TestCacheStrategy cacheStrategy;
    private final CacheManager cacheManager;

    public TestCacheService(TestCacheStrategy cacheStrategy, CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        this.cacheStrategy = cacheStrategy;
    }

    public void putEntry(String key, Object o) {
        Cache cache = cacheManager.getCache(getRegionDependingOnStrategy(this.cacheStrategy));
        Element element = new Element(key, o);
        cache.putIfAbsent(element);
    }

    public Object getEntry(String key) {
        Cache cache = cacheManager.getCache(getRegionDependingOnStrategy(this.cacheStrategy));
        Element e = cache.get(key);
        if (e == null) {
            return null;
        }
        return e.getObjectValue();
    }

    private String getRegionDependingOnStrategy(TestCacheStrategy cacheStrategy) {
        if (cacheStrategy == TestCacheStrategy.LRU) {
            return "lru_region";
        }
        return "lfu_region";
    }
}
