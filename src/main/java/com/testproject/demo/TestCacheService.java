package com.testproject.demo;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestCacheService {

    static Long l = 0L;

    @Autowired
    private CacheManager cacheManager;

    public void putEntry(String key, Object o) {
        Cache cache = cacheManager.getCache("lru_region");
        Element element = new Element(key, o);
        cache.putIfAbsent(element);
    }

    public Object getEntry(String key) {
        Cache cache = cacheManager.getCache("lru_region");
        Element e = cache.get(key);
        if (e == null) {
            return null;
        }
        return e.getObjectValue();
    }

    @CachePut(value = "lru_region", key="#key")
    public Object putEntryInternal(String key, Object o) {
        return o;
    }
}
