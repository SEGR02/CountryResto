package ar.com.country.restaurant.exceptions.translators;

import ar.com.country.restaurant.exceptions.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.*;
import org.zalando.problem.spring.web.advice.AdviceTrait;
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationProblem;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestControllerAdvice
public class ApplicationExceptionTranslator implements AdviceTrait {
    private static final String PATH_KEY = "path";
    private static final String VIOLATIONS_KEY = "violations";

    @Override
    public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity, @NonNull NativeWebRequest request) {
        if (isNull(entity)) {
            return null;
        }
        Problem problem = entity.getBody();
        if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {
            return entity;
        }

        HttpServletRequest nativeRequest = request.getNativeRequest(HttpServletRequest.class);
        String requestUri = nonNull(nativeRequest) ? nativeRequest.getRequestURI() : StringUtils.EMPTY;
        ProblemBuilder builder = Problem.builder()
                .withType(Problem.DEFAULT_TYPE.equals(problem.getType()) ? ErrorConstants.DEFAULT_TYPE : problem.getType())
                .withTitle(problem.getTitle())
                .withStatus(problem.getStatus())
                .withDetail(problem.getDetail())
                .with(PATH_KEY, requestUri);
        if (problem instanceof ConstraintViolationProblem) {
            builder.with(VIOLATIONS_KEY, ((ConstraintViolationProblem) problem).getViolations());
        } else {
            builder.withCause(((DefaultProblem) problem).getCause()).withDetail(problem.getDetail()).withInstance(problem.getInstance());
            problem.getParameters().forEach(builder::with);
        }

        ThrowableProblem throwableProblem = builder.build();
        throwableProblem.setStackTrace(((ThrowableProblem) problem).getStackTrace());
        return new ResponseEntity<>(problem, entity.getHeaders(), entity.getStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<Problem> badCredentialsHandler(final BadCredentialsException e, NativeWebRequest request) {
        return create(Status.BAD_REQUEST, e, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> emailAlreadyTakenHandler(final EmailAlreadyTakenException e, NativeWebRequest request) {
        return create(Status.CONFLICT, e, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> userNotFoundHandler(final UserNotFoundException e, NativeWebRequest request) {
        return create(Status.NOT_FOUND, e, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> addressNotFoundHandler(final AddressNotFoundException e, NativeWebRequest request) {
        return create(Status.NOT_FOUND, e, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> paymentMethodNotFoundHandler(final PaymentMethodNotFoundException e, NativeWebRequest request) {
        return create(Status.NOT_FOUND, e, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> cardNumberAlreadyExistsHandler(final CardNumberAlreadyExistsException e, NativeWebRequest request) {
        return create(Status.CONFLICT, e, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> dishNotFoundExceptionHandler(final DishNotFoundException e, NativeWebRequest request) {
        return create(Status.NOT_FOUND, e, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> dishCategoryNotFoundExceptionHandler(final DishCategoryNotFoundException e, NativeWebRequest request) {
        return create(Status.NOT_FOUND, e, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> dishCategoryNameAlreadyExceptionHandler(final DishCategoryNameAlreadyExistsException e, NativeWebRequest request) {
        return create(Status.CONFLICT, e, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> commentNotFoundExceptionHandler(final CommentNotFoundException e, NativeWebRequest request) {
        return create(Status.NOT_FOUND, e, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> userNotOwnerOfCommentExceptionHandler(final UserNotOwnerOfCommentException e, NativeWebRequest request) {
        return create(Status.FORBIDDEN, e, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> itemNotFoundExceptionHandler(final ItemNotFoundException e, NativeWebRequest request) {
        return create(Status.NOT_FOUND, e, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> orderNotFoundExceptionHandler(final OrderNotFoundException e, NativeWebRequest request) {
        return create(Status.NOT_FOUND, e, request);
    }

}
