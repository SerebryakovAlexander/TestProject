package com.testproject.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	@Qualifier("lfu_cache_service")
	private TestCacheService testLfuCacheService;
	@Autowired
	@Qualifier("lru_cache_service")
	private TestCacheService testLruCacheService;

	@Test
	public void testLfuCacheServiceReturnsNullWhenNotFound() {
		TestCacheableEntity result = (TestCacheableEntity)testLfuCacheService.getEntry("key");

		Assert.assertNull(result);
	}

	@Test
	public void testLfuCacheServicePutIsOk() {
		TestCacheableEntity testCacheableEntity = new TestCacheableEntity("hello world");

		testLfuCacheService.putEntry("key1", testCacheableEntity);

		TestCacheableEntity result = (TestCacheableEntity)testLfuCacheService.getEntry("key1");

		assertAll(
				() -> Assert.assertEquals(testCacheableEntity, result),
				() -> Assert.assertEquals("hello world", result.getString())
		);
	}

	@Test
	public void testLruCacheServiceReturnsNullWhenNotFound() {
		TestCacheableEntity result = (TestCacheableEntity)testLruCacheService.getEntry("key");

		Assert.assertNull(result);
	}

	@Test
	public void testLruCacheServicePutIsOk() {
		TestCacheableEntity testCacheableEntity = new TestCacheableEntity("hello world");

		testLruCacheService.putEntry("key1", testCacheableEntity);

		TestCacheableEntity result = (TestCacheableEntity)testLruCacheService.getEntry("key1");

		assertAll(
				() -> Assert.assertEquals(testCacheableEntity, result),
				() -> Assert.assertEquals("hello world", result.getString())
		);
	}
}
