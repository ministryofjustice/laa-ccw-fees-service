package uk.gov.laa.ccw.validators;

import uk.gov.laa.ccw.exceptions.MissingDataException;

/**
 * Matter code request validation.
 */
public class MatterCodesValidator {
    /**
     * Validate the matter code request is allowed.
     *
     * @param lawType the requested law type
     */
    public static void validateRequest(String lawType) throws MissingDataException {
        if (lawType == null || lawType.isEmpty()) {
            throw new MissingDataException("No law type provided");
        }

    }
}
