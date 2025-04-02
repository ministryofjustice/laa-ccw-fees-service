package uk.gov.laa.ccw.exceptions;

/**
 * The exception thrown when database read error.
 */
public class DatabaseReadException extends RuntimeException {

    /**
     * Constructor for DatabaseReadException.
     *
     * @param message the error message
     */
    public DatabaseReadException(String message) {
        super(message);
    }
}