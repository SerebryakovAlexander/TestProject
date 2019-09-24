package com.testproject.demo;

import java.util.HashMap;

public class LfuEvictionStrategy implements EvictionStrategy {

    protected HashMap<String, Long> priorityMap = new HashMap<>();

    public void addKey(String key) {
        priorityMap.putIfAbsent(key, 0L);
    }
    public void updateKey(String key) {
        if (priorityMap.containsKey(key)) {
            priorityMap.put(key, priorityMap.get(key) + 1);
        }
    }
    public void removeKey(String key) {
        priorityMap.remove(key);
    }
    public String getMinKeyValue() {
        String minKey = null;
        Long minValue = Long.MAX_VALUE;
        for (HashMap.Entry e : priorityMap.entrySet()) {
            if ((Long)e.getValue() < minValue) {
                minValue = (Long)e.getValue();
                minKey = (String)e.getKey();
            }
        }
        return minKey;
    }

}
