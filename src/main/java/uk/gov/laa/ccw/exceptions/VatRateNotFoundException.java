package uk.gov.laa.ccw.exceptions;

/**
 * The exception to represent vat rates not found.
 */
public class VatRateNotFoundException extends RuntimeException {

    /**
     * Constructor for VatRateNotFoundException.
     *
     * @param message the error message
     */
    public VatRateNotFoundException(String message) {
        super(message);
    }
}