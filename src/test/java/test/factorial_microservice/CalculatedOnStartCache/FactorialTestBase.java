package test.factorial_microservice.CalculatedOnStartCache;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

public abstract class FactorialTestBase {

    protected static final int dynamicMaxValue  = 100;

    @DynamicPropertySource
    static void factorialProperties(DynamicPropertyRegistry registry) {
        registry.add("app.factorial.max-value", () -> dynamicMaxValue );
    }
}