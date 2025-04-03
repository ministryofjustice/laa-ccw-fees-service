package uk.gov.laa.ccw.validators;

import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.models.api.CaseStageRequest;

public class CaseStageValidator {
    public static void validateRequest(CaseStageRequest request) throws MissingDataException {
        if (request.getMatterCode1() == null || request.getMatterCode1().isEmpty()) {
            throw new MissingDataException("No matter code 1 provided");
        }

        if (request.getMatterCode2() == null || request.getMatterCode2().isEmpty()) {
            throw new MissingDataException("No matter code 2 provided");
        }
    }

}
