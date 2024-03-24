package test.factorial_microservice.exception;

/**
 * Exception thrown when a value that has passed validation is nevertheless outside the acceptable range for the factorial cache.
 * This might occur if the value is less than 0 or exceeds the maximum size configured for the cache.
 */
public class InvalidFactorialArgumentException extends RuntimeException {
    public InvalidFactorialArgumentException(String message) {
        super(message);
    }
}