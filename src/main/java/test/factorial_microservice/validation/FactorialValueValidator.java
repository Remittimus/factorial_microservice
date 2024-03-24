package test.factorial_microservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import test.factorial_microservice.configuration.FactorialCacheConfiguration;


/**
 * Validator for {@link ValueForFactorial} annotation that checks if the field's value
 * is within the allowed range for factorial computation.
 * The number must be non-negative integer and not exceed the maximum allowable size defined in {@link FactorialCacheConfiguration}.
 */
@Component
@AllArgsConstructor
public class FactorialValueValidator implements ConstraintValidator<ValueForFactorial, String> {

    private final FactorialCacheConfiguration cacheConfig;

    @Override
    public void initialize(ValueForFactorial constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null||value.isEmpty()||!value.matches("^\\d+$")) {
            setCommonMessage(context);
            return false;
        }
        try {
            int valueForCheck = Integer.parseInt(value);
            if (valueForCheck < 0 || valueForCheck > cacheConfig.getMaxSize()) {
                context.disableDefaultConstraintViolation();
                setCommonMessage(context);
                return false;
            }
        }catch (NumberFormatException e) {
            setCommonMessage(context);
            return false;
        }
        return true;
    }

    private void setCommonMessage(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        String message = "Expected request body to contain one argument with the key 'factorial_num' and an integer value ranging from 0 to " + cacheConfig.getMaxSize() + ", inclusive.";
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}