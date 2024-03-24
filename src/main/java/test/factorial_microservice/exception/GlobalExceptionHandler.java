package test.factorial_microservice.exception;

import io.micrometer.core.annotation.Timed;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Global exception handler for the application, handling various exceptions and providing appropriate HTTP responses.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * Handles validation exceptions thrown when request data does not meet validation constraints.
     * @param ex the exception that was thrown
     * @param request the HTTP request during which the exception was thrown
     * @return a map containing error details, including a timestamp, status code, and error message
     */
    @Timed("validation.failed")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorDetails = ex.getFieldError() != null ? ex.getFieldError().getDefaultMessage() : "Validation error";
        return buildResponse(errorDetails, request);
    }

    /**
     * Handles {@link InvalidFactorialArgumentException}  exceptions thrown when value is valid but out of range cache size.
     * @param ex the exception that was thrown
     * @param request the HTTP request during which the exception was thrown
     * @return a map containing error details, including a timestamp, status code, and error message
     */
    @Timed("invalid.factorial.value")
    @ExceptionHandler(InvalidFactorialArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleInvalidFactorialArgumentException(InvalidFactorialArgumentException ex, HttpServletRequest request) {
        return buildResponse(ex.getMessage(), request);
    }



    private Map<String, Object> buildResponse(String message, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST);
        body.put("path", request.getRequestURI());
        body.put("details", message);
        log.info(body.toString());
        return body;
    }


}
