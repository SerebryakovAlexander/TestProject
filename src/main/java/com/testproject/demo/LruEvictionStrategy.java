package com.testproject.demo;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;

public class LruEvictionStrategy implements EvictionStrategy {
    protected Deque<String> priorityQueue = new ArrayDeque<>();

    public void addKey(String key) {
        priorityQueue.push(key);
    }
    public void updateKey(String key) {
        if (priorityQueue.contains(key)) {
            priorityQueue.removeFirstOccurrence(key);
            priorityQueue.push(key);
        }
    }
    public void removeKey(String key) {
        priorityQueue.removeFirstOccurrence(key);
    }
    public String getMinKeyValue() {
        if (priorityQueue.size() > 0) {
            return priorityQueue.getLast();
        }
        return null;
    }
}
