package com.testproject.demo;

public interface EvictionStrategy {
    void addKey(String key);
    void updateKey(String key);
    void removeKey(String key);
    String getMinKeyValue();
}
