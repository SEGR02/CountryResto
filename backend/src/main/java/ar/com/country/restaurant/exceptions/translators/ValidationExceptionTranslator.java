package ar.com.country.restaurant.exceptions.translators;

import ar.com.country.restaurant.exceptions.ErrorConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationAdviceTrait;
import org.zalando.problem.spring.web.advice.validation.MethodArgumentNotValidAdviceTrait;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionTranslator implements ConstraintViolationAdviceTrait, MethodArgumentNotValidAdviceTrait {
    private static final String FIELD_ERRORS_KEY = "fieldErrors";

    @Override
    public ResponseEntity<Problem> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull NativeWebRequest request) {
        Map<String, String> validationErrors = extractValidationErrors(ex);
        Problem problem = Problem.builder()
                .withType(ErrorConstants.CONSTRAINT_VIOLATION_TYPE)
                .withTitle("Method argument not valid")
                .withStatus(defaultConstraintViolationStatus())
                .with(FIELD_ERRORS_KEY, validationErrors)
                .build();
        return create(ex, problem, request);
    }

    private Map<String, String> extractValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> result = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                error -> result.put(((FieldError) error).getField(), error.getDefaultMessage())
        );
        return result;
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, NativeWebRequest request) {
        return create(Status.BAD_REQUEST, ex, request);
    }

}
