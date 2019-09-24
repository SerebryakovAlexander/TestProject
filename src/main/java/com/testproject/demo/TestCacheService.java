package com.testproject.demo;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.HashMap;


public class TestCacheService {

    private final int maxSize;
    private final EvictionStrategy evictionStrategy;
    private final HashMap<String, Object> objectMap = new HashMap<>();

    public TestCacheService(int maxSize, EvictionStrategy evictionStrategy) {
        this.maxSize = maxSize;
        this.evictionStrategy = evictionStrategy;
    }

    public void putEntry(String key, Object o) {
        if (objectMap.containsKey(key)) {
            objectMap.put(key, o);
            evictionStrategy.updateKey(key);
        }
        else if (objectMap.size() < maxSize) {
            objectMap.put(key, o);
            evictionStrategy.addKey(key);
        }
        else {
            String minKey = evictionStrategy.getMinKeyValue();
            objectMap.remove(minKey);
            objectMap.put(key, o);
            evictionStrategy.addKey(key);
            evictionStrategy.removeKey(minKey);
        }
    }

    public Object getEntry(String key) {
        if (objectMap.containsKey(key)) {
            evictionStrategy.updateKey(key);
            return objectMap.get(key);
        }
        return null;
    }

}
