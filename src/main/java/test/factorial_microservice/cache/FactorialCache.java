package test.factorial_microservice.cache;

import test.factorial_microservice.exception.InvalidFactorialArgumentException;

import java.math.BigInteger;

/**
 * Interface for a cache that stores precomputed factorial values.
 */
public interface FactorialCache {

    /**
     * Retrieves the factorial of a specified number.
     * The method ensures that the provided number is within the acceptable range (0 to the maximum cache size).
     * If the number is outside this range, an {@link InvalidFactorialArgumentException} is thrown.
     * For cache implementations that do not depend on the maximum size, no exception will be thrown.
     *
     * @param number The number to retrieve the factorial of. Must be between 0 and the maximum size set for the factorial cache.
     * @return The factorial value as a {@link BigInteger}.
     * @throws InvalidFactorialArgumentException if the number is less than 0 or greater than the maximum cache size.
     */
     BigInteger getFactorialOf(int number);
}
