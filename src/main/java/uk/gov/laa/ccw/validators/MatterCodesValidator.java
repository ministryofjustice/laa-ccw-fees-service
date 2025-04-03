package uk.gov.laa.ccw.validators;

import uk.gov.laa.ccw.exceptions.MissingDataException;

public class MatterCodesValidator {
    public static void validateRequest(String id) throws MissingDataException {
        if (id == null || id.isEmpty()) {
            throw new MissingDataException("No law type provided");
        }

    }
}
