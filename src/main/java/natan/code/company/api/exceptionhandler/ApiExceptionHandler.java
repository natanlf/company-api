package natan.code.company.api.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import natan.code.company.domain.exception.EntityInUseException;
import natan.code.company.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String GENERIC_ERROR_MESSAGE
            = "An unexpected internal system error has occurred. " +
            "Please try again and if the problem persists, contact your system administrator.";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.NOT_FOUND_RESOURCE;
        String detail = e.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_ERROR_MESSAGE).build();

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntityInUseException e, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_IN_USE;
        String detail = e.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail).build();

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                    .title(status.toString())
                    .status(status.value())
                    .userMessage(GENERIC_ERROR_MESSAGE)
                    .timestamp(OffsetDateTime.now())
                    .build();
        }

        log.error("handleValidationInternal: {}", ex.getMessage());

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {

        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .timestamp(OffsetDateTime.now())
                .detail(detail);
    }

}
