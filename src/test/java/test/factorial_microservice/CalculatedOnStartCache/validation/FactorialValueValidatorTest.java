package test.factorial_microservice.CalculatedOnStartCache.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import jakarta.validation.ConstraintValidatorContext;
import test.factorial_microservice.configuration.FactorialCacheConfiguration;
import test.factorial_microservice.validation.FactorialValueValidator;

class FactorialValueValidatorTest {

    @Mock
    private FactorialCacheConfiguration cacheConfig;
    @Mock
    private ConstraintValidatorContext context;
    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;
    private FactorialValueValidator validator;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(cacheConfig.getMaxSize()).thenReturn(100);
        validator = new FactorialValueValidator(cacheConfig);

        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);
        when(constraintViolationBuilder.addConstraintViolation()).thenReturn(context);
    }

    @Test
    void whenValueIsValid_thenValidationSucceeds() {
        assertTrue(validator.isValid("5", context), "Validation should pass for a valid value within the range");
    }

    @Test
    void whenValueIsInvalid_thenValidationFails() {
        assertFalse(validator.isValid("-1", context), "Validation should fail for a value less than 0");
        assertFalse(validator.isValid("101", context), "Validation should fail for a value greater than the max size");
        assertFalse(validator.isValid("a little bit tired", context), "Validation should fail for a non-numeric value");
    }
}
