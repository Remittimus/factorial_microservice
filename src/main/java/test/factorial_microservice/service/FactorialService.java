package test.factorial_microservice.service;

import test.factorial_microservice.dto.FactorialResponseDto;

/**
 * Service interface for factorial computation.
 */
public interface FactorialService {
    /**
     * Computes the factorial of the given number.
     * @param factorialNum the number to compute the factorial of
     * @return a {@link FactorialResponseDto} containing the factorial result
     */
     FactorialResponseDto getFactorialOf(int factorialNum);
}
