package uk.gov.laa.ccw.exceptions;

public class MissingDataException extends RuntimeException {
    public MissingDataException(String message) {
        super(message);
    }
}