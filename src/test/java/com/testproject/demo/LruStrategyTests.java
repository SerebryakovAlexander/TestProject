package com.testproject.demo;

import org.junit.Assert;
import org.junit.Test;

public class LruStrategyTests {

    @Test
    public void testLfuStrategySingleAddIsOk() {
        LruEvictionStrategy lru = new LruEvictionStrategy();
        lru.addKey("K1");
        Assert.assertEquals("K1", lru.getMinKeyValue());
    }
    @Test
    public void testLfuStrategyDoubleAddIsOk() {
        LruEvictionStrategy lru = new LruEvictionStrategy();
        lru.addKey("K1");
        lru.addKey("K2");
        Assert.assertEquals("K1", lru.getMinKeyValue());
    }
    @Test
    public void testLfuStrategyGetMinValueIsOk() throws InterruptedException {
        LruEvictionStrategy lru = new LruEvictionStrategy();

        lru.addKey("K1");
        lru.addKey("K2");
        lru.addKey("K3");

        Thread.sleep(10);
        lru.updateKey("K1");
        Thread.sleep(10);
        lru.updateKey("K3");

        Assert.assertEquals("K2", lru.getMinKeyValue());
    }

}
