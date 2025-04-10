package uk.gov.laa.ccw.exceptions;

/**
 * The exception to represent invoices.
 */
public class InvoiceException extends RuntimeException {

    /**
     * Constructor for InvoiceException.
     *
     * @param message the error message
     */
    public InvoiceException(String message) {
        super(message);
    }
}
