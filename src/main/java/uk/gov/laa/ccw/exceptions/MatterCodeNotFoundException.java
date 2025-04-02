package uk.gov.laa.ccw.exceptions;

/**
 * The exception thrown when matter code not found.
 */
public class MatterCodeNotFoundException extends RuntimeException {

    /**
     * Constructor for MatterCodeNotFoundException.
     *
     * @param message the error message
     */
    public MatterCodeNotFoundException(String message) {
        super(message);
    }
}
