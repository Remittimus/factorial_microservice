package test.factorial_microservice.CalculatedOnStartCache.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.factorial_microservice.CalculatedOnStartCache.FactorialTestBase;
import test.factorial_microservice.cache.CalculatedOnStartFactorialCache;
import test.factorial_microservice.exception.InvalidFactorialArgumentException;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CalculatedOnStartFactorialCacheTest extends FactorialTestBase {

    @Autowired
    private CalculatedOnStartFactorialCache factorialCache;


    @Test
    public void testFactorialValues() {
        assertEquals(BigInteger.valueOf(1), factorialCache.getFactorialOf(0), "Factorial of 0 should be 1");
        if(dynamicMaxValue>=100) {
            assertEquals(new BigInteger("93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000"), factorialCache.getFactorialOf(100), "Factorial of 100 must be correct");
        }
    }
    @Test
    public void whenNumberOutOfRange_thenThrowsInvalidFactorialArgumentException() {
        assertThrows(InvalidFactorialArgumentException.class, () -> factorialCache.getFactorialOf(-1));
        assertThrows(InvalidFactorialArgumentException.class, () -> factorialCache.getFactorialOf(factorialCache.getSize() + 1));
    }
}
