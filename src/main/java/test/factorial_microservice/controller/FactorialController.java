package test.factorial_microservice.controller;



import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.factorial_microservice.dto.FactorialRequestDto;
import test.factorial_microservice.dto.FactorialResponseDto;
import test.factorial_microservice.service.FactorialService;

/**
 * REST Controller for handling factorial computation requests.
 * Validates input and delegates the computation to the {@link FactorialService}.
 */
@RestController
@AllArgsConstructor
public class FactorialController {

    final FactorialService factorialService;

    /**
     * Handles POST requests for factorial computation.
     * Expects a JSON object with a single key "factorial_num" indicating the number to compute the factorial of.
     * The number must be in the range 0 to 100, inclusive.
     *
     * @param request the request payload containing the number for which to compute the factorial
     * @return a {@link FactorialResponseDto} containing the result of the factorial computation
     */
    @PostMapping("/factorial")
    public FactorialResponseDto factorialController(@Valid @RequestBody FactorialRequestDto request) {
        return factorialService.getFactorialOf(Integer.parseInt(request.factorialNum()));
    }
}
