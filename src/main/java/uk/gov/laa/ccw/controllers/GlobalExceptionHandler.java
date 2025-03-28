package uk.gov.laa.ccw.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.models.HttpError500Response;

import static org.springframework.http.ResponseEntity.internalServerError;

@Slf4j
@ControllerAdvice
@SuppressWarnings({"java:S1171", "java:S3599"}) //Disabling due to generated code
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DatabaseReadException.class)
    public ResponseEntity<HttpError500Response> handleDatabaseReadException(DatabaseReadException e) {
        var response = new HttpError500Response() {{
            setError(e.getMessage());
        }};

        log.error("DatabaseReadException Thrown: %s".formatted(response));
        log.error("DatabaseReadException stacktrace: %s".formatted(e.getStackTrace()));

        return internalServerError().body(response);
    }
}
