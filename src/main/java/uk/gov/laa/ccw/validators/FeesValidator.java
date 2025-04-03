package uk.gov.laa.ccw.validators;

import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.models.api.FeeRequest;

public class FeesValidator  {
    public static void validateRequest(FeeRequest request) throws MissingDataException {
        if (request.getMatterCode1() == null || request.getMatterCode1().isEmpty()) {
            throw new MissingDataException("No matter code 1 provided");
        }
        if (request.getMatterCode2() == null || request.getMatterCode2().isEmpty()) {
            throw new MissingDataException("No matter code 2 provided");
        }
        if (request.getLocationCode() == null || request.getLocationCode().isEmpty()) {
            throw new MissingDataException("No provider location provided");
        }
        if (request.getCaseStage() == null || request.getCaseStage().isEmpty()) {
            throw new MissingDataException("No case stage provided");
        }

    }
}