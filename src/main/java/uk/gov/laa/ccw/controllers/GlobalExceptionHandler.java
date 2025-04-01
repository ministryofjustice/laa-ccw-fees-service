package uk.gov.laa.ccw.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.models.HttpError400Response;
import uk.gov.laa.ccw.models.HttpError404Response;
import uk.gov.laa.ccw.models.HttpError500Response;

import static org.springframework.http.ResponseEntity.internalServerError;

@Slf4j
@ControllerAdvice
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MatterCodeNotFoundException.class)
    public ResponseEntity<HttpError404Response> handleMatterCodeNotFoundException(
            MatterCodeNotFoundException e) {
        var response = new HttpError404Response() {{
            setError(e.getMessage());
        }};

        log.error("DatabaseReadException Thrown: %s".formatted(response));
        log.error("DatabaseReadException stacktrace: %s".formatted(e.getStackTrace()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingDataException.class)
    public ResponseEntity<HttpError400Response> handleMissingDataException(
            MissingDataException e) {
        var response = new HttpError400Response() {{
            setError(e.getMessage());
        }};

        log.error("DatabaseReadException Thrown: %s".formatted(response));
        log.error("DatabaseReadException stacktrace: %s".formatted(e.getStackTrace()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
