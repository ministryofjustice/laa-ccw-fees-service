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
import uk.gov.laa.ccw.models.api.HttpError400Response;
import uk.gov.laa.ccw.models.api.HttpError404Response;
import uk.gov.laa.ccw.models.api.HttpError500Response;

import static org.springframework.http.ResponseEntity.internalServerError;

/**
 * The global exception handler for all exceptions.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles DatabaseReadException.
     *
     * @param exception the database read exception
     * @return the Internal Server Error response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DatabaseReadException.class)
    public ResponseEntity<HttpError500Response> handleDatabaseReadException(
            DatabaseReadException exception) {
        var response = HttpError500Response.builder().error(exception.getMessage()).build();

        log.error("DatabaseReadException Thrown: %s".formatted(response));
        log.error("DatabaseReadException stacktrace: %s".formatted(exception.getStackTrace()));

        return internalServerError().body(response);
    }

    /**
     * Handles NumberFormatException.
     *
     * @param exception the number format exception
     * @return the Bad Request response
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MatterCodeNotFoundException.class)
    public ResponseEntity<HttpError404Response> handleMatterCodeNotFoundException(
            MatterCodeNotFoundException exception) {
        var response = HttpError404Response.builder().error(exception.getMessage()).build();

        log.error("MatterCodeNotFoundException Thrown: %s".formatted(response));
        log.error("MatterCodeNotFoundException stacktrace: %s".formatted(exception.getStackTrace()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Handles MissingDataException.
     *
     * @param exception the missing data exception
     * @return the Bad Request response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingDataException.class)
    public ResponseEntity<HttpError400Response> handleMissingDataException(
            MissingDataException exception) {
        var response = HttpError400Response.builder().error(exception.getMessage()).build();

        log.error("MissingDataException Thrown: %s".formatted(response));
        log.error("MissingDataException stacktrace: %s".formatted(exception.getStackTrace()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
