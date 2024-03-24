package test.factorial_microservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Validation annotation to ensure that a given integer is within the acceptable range for factorial computation.
 * The value is validated against the configured maximum size for factorial calculations to ensure it's within the cache limit.
 * This annotation should be applied to strings fields.
 */
@Documented
@Constraint(validatedBy = FactorialValueValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueForFactorial {
    String message() default "The number must be less than or equal to the maximum size";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}