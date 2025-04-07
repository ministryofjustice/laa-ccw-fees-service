package uk.gov.laa.ccw.exceptions;

/**
 * The exception to represent case stages not found.
 */
public class CaseStagesNotFoundException extends RuntimeException {

    /**
     * Constructor for case stages not found exception.
     */
    public CaseStagesNotFoundException(String message) {
        super(message);
    }
}