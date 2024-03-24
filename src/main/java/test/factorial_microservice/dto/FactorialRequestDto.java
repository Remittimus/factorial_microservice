package test.factorial_microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import test.factorial_microservice.validation.ValueForFactorial;


public record FactorialRequestDto(
        @JsonProperty("factorial_num")
        @ValueForFactorial
        String factorialNum
) { }
