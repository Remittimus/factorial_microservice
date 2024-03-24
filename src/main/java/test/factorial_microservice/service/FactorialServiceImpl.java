package test.factorial_microservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import test.factorial_microservice.cache.FactorialCache;
import test.factorial_microservice.dto.FactorialResponseDto;

/**
 * Implementation of {@link FactorialService} that computes factorials, potentially leveraging caching.
 */
@Service
@AllArgsConstructor
public class FactorialServiceImpl implements FactorialService{

    final FactorialCache factorialCache;


    @Override
    public FactorialResponseDto getFactorialOf(int factorialNum) {
        return new FactorialResponseDto(factorialCache.getFactorialOf(factorialNum));
    }
}
