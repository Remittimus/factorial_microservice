package test.factorial_microservice.CalculatedOnStartCache.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import test.factorial_microservice.CalculatedOnStartCache.FactorialTestBase;
import test.factorial_microservice.cache.FactorialCache;
import test.factorial_microservice.dto.FactorialResponseDto;
import test.factorial_microservice.service.FactorialServiceImpl;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FactorialServiceImplTest {

    @Mock
    private FactorialCache factorialCache;

    @InjectMocks
    private FactorialServiceImpl factorialService;



    @Test
    public void whenValidNumber_thenFactorialIsReturned() {
        when(factorialCache.getFactorialOf(5)).thenReturn(BigInteger.valueOf(120));

        FactorialResponseDto result = factorialService.getFactorialOf(5);

        assertEquals(120, result.result().intValue());
        verify(factorialCache, times(1)).getFactorialOf(5);
    }
}