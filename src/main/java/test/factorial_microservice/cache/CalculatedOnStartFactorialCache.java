package test.factorial_microservice.cache;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import test.factorial_microservice.configuration.FactorialCacheConfiguration;
import test.factorial_microservice.exception.InvalidFactorialArgumentException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link FactorialCache} implementation that precomputes and caches factorial values upon initialization.
 */
@Component
@Slf4j
public class CalculatedOnStartFactorialCache implements FactorialCache {

    List<BigInteger> factorials;



    @Getter
    private final int size;

    private CalculatedOnStartFactorialCache(FactorialCacheConfiguration cacheConfiguration){
        this.size = cacheConfiguration.getMaxSize();
        factorials = new ArrayList<>(size+1);
        log.info("Factorial calculation started");
        factorials.add(BigInteger.ONE);
        for (int i = 1; i < 101; i++) {
            factorials.add(factorials.get(i - 1).multiply(BigInteger.valueOf(i)));
        }
        log.info("Factorial calculation ended");
    }


    @Override
    public BigInteger getFactorialOf(int number) {

        if (number < 0 || number > this.getSize()) {
            throw new InvalidFactorialArgumentException("Factorial number must be between 0 and "+this.getSize());
        }
        return factorials.get(number);
    }
}
