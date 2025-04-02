package uk.gov.laa.ccw.exceptions;

/**
 * The exception thrown when data is missing.
 */
public class MissingDataException extends RuntimeException {

    /**
     * Constructor for MissingDataException.
     *
     * @param message the error message
     */
    public MissingDataException(String message) {
        super(message);
    }
}