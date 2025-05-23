package uk.gov.laa.ccw.services.validators;

import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.model.api.InvoiceCreateRequest;

/**
 * Validates the invoice request.
 *
 */
@Service
public class InvoiceCreationValidator {
    /**
     * Validates the invoice request.
     *
     */
    public void validateRequest(InvoiceCreateRequest request) {
        if (request.getProvider() == null || request.getProvider().isEmpty()) {
            throw new MissingDataException("No provider provided");
        }

        if (request.getOffice() == null || request.getOffice().isEmpty()) {
            throw new MissingDataException("No office provided");
        }

        if (request.getAmount() == null) {
            throw new MissingDataException("No amount provided");
        }

    }
}
