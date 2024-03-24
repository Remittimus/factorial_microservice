package test.factorial_microservice.configuration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



/**
 * Configuration class for factorial cache settings, including the maximum cache size.
 * The maximum size is validated to ensure it's greater than 0. If the configured value is invalid (e.g., negative),
 * an {@link IllegalArgumentException} is thrown to prevent application startup with invalid cache settings.
 */

@Getter
@Component
@Slf4j
public class FactorialCacheConfiguration {

    private final int maxSize;

    public FactorialCacheConfiguration(@Value("${app.factorial.max-value:100}") int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Illegal factorial cache size. cache size must be >0");
        }else{
            this.maxSize = maxSize;

        }
    }

}
