package uk.gov.laa.ccw.exceptions;

/**
 * The exception to represent fees not found.
 */

public class FeesException extends RuntimeException {

    /**
     * Constructor for MatterCodeNotFoundException.
     *
     * @param message the error message
     */
    public FeesException(String message) {
        super(message);
    }
}