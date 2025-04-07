package uk.gov.laa.ccw.controllers;

import org.junit.jupiter.api.Test;
import uk.gov.laa.ccw.exceptions.FeesException;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.exceptions.VatRateNotFoundException;
import uk.gov.laa.ccw.exceptions.CaseStagesNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class GlobalExceptionHandlerTest {
    private static final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void shouldHandleMatterCodeNotFoundException() {
        // Given
        var exception = new MatterCodeNotFoundException("exception");

        // When
        var response = globalExceptionHandler.handleMatterCodeNotFoundException(exception);

        // Then
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertEquals("exception", response.getBody().getError());
    }

    @Test
    void shouldHandleVatRateNotFoundException() {
        // Given
        var exception = new VatRateNotFoundException("exception");

        // When
        var response = globalExceptionHandler.handleVatRateNotFoundException(exception);

        // Then
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertEquals("exception", response.getBody().getError());
    }

    @Test
    void shouldHandleCaseStagesNotFoundException() {
        // Given
        var exception = new CaseStagesNotFoundException("exception");

        // When
        var response = globalExceptionHandler.handleCaseStagesNotFoundException(exception);

        // Then
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertEquals("exception", response.getBody().getError());
    }

    @Test
    void shouldHandleFeesException() {
        // Given
        var exception = new FeesException("exception");

        // When
        var response = globalExceptionHandler.handleFeesException(exception);

        // Then
        assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("exception", response.getBody().getError());
    }
}