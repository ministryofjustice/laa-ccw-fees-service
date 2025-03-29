package uk.gov.laa.ccw.exceptions;

public class MatterCodeNotFoundException extends RuntimeException {
    public MatterCodeNotFoundException(String message) {
        super(message);
    }
}
