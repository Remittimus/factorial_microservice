package test.factorial_microservice.CalculatedOnStartCache.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.factorial_microservice.cache.CalculatedOnStartFactorialCache;
import test.factorial_microservice.configuration.FactorialCacheConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FactorialCacheConfigurationTest {

    @Autowired
    private CalculatedOnStartFactorialCache factorialCache;

    @Autowired
    private FactorialCacheConfiguration cacheConfiguration;

    @Test
    public void testCacheSizeMatchesConfiguration() {
        assertEquals(cacheConfiguration.getMaxSize(), factorialCache.getSize(), "Cache size should match configuration");
    }
}