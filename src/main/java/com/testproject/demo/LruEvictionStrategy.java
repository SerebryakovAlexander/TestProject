package com.testproject.demo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

public class LruEvictionStrategy implements EvictionStrategy {
    protected HashMap<String, LocalDateTime> priorityMap = new HashMap<>();

    public void addKey(String key) {
        priorityMap.putIfAbsent(key, LocalDateTime.now());
    }
    public void updateKey(String key) {
        if (priorityMap.containsKey(key)) {
            priorityMap.put(key, LocalDateTime.now());
        }
    }
    public void removeKey(String key) {
        priorityMap.remove(key);
    }
    public String getMinKeyValue() {
        String minKey = null;
        LocalDateTime minValue = LocalDateTime.MAX;
        for (HashMap.Entry e : priorityMap.entrySet()) {
            if (((LocalDateTime)e.getValue()).isBefore(minValue)) {
                minValue = (LocalDateTime)e.getValue();
                minKey = (String)e.getKey();
            }
        }
        return minKey;
    }
}
