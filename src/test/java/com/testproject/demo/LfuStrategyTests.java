package com.testproject.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LfuStrategyTests {

    @Test
    public void testLfuStrategySingleAddIsOk() {
        LfuEvictionStrategy lfu = new LfuEvictionStrategy();
        lfu.addKey("K1");
        Assert.assertEquals("K1", lfu.getMinKeyValue());
    }
    @Test
    public void testLfuStrategyDoubleAddIsOk() {
        LfuEvictionStrategy lfu = new LfuEvictionStrategy();
        lfu.addKey("K1");
        lfu.addKey("K2");
        Assert.assertEquals("K1", lfu.getMinKeyValue());
    }
    @Test
    public void testLfuStrategyGetMinValueIsOk() {
        LfuEvictionStrategy lfu = new LfuEvictionStrategy();

        lfu.addKey("K1");
        lfu.addKey("K2");
        lfu.addKey("K3");

        lfu.updateKey("K1");
        lfu.updateKey("K3");

        Assert.assertEquals("K2", lfu.getMinKeyValue());
    }
}
