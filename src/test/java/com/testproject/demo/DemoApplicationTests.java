package com.testproject.demo;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private TestCacheService testCacheService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCacheServiceReturnsNullWhenNotFound() {
		TestCacheableEntity result = (TestCacheableEntity)testCacheService.getEntry("key");

		Assert.assertNull(result);
	}

	@Test
	public void testCacheServicePutIsOk() {
		TestCacheableEntity testCacheableEntity = new TestCacheableEntity("hello world");

		testCacheService.putEntry("key1", testCacheableEntity);

		TestCacheableEntity result = (TestCacheableEntity)testCacheService.getEntry("key1");

		Assert.assertEquals(testCacheableEntity, result);
		Assert.assertEquals("hello world", result.getString());
	}

}
