package uk.gov.laa.ccw.services.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.model.api.FeeCalculateRequest;
import uk.gov.laa.ccw.model.api.FeeListAvailableRequest;

import java.util.ArrayList;

/**
 * Validator class for fees.
 */
@Service
public class FeeCalculateValidator {

    /**
     * Validates the fee calculator request.
     *
     * @param request the fee calculator request
     */
    public void validateFeeCalculateRequest(FeeCalculateRequest request) throws MissingDataException {
        validateCoreFields(request.getMatterCode1(),
                request.getMatterCode2(),
                request.getLocationCode(),
                request.getCaseStage()
        );

        if (request.getLevelCodes() == null) {
            request.setLevelCodes(new ArrayList<>());
        }
    }

    /**
     * Validates the fee list available request.
     *
     * @param request the fee list available request
     */
    public void validateFeeListAvailableRequest(FeeListAvailableRequest request) throws MissingDataException {
        validateCoreFields(request.getMatterCode1(),
                request.getMatterCode2(),
                request.getLocationCode(),
                request.getCaseStage()
        );
    }

    private void validateCoreFields(
            String matterCode1,
            String matterCode2,
            String locationCode,
            String caseStage) {
        if (matterCode1 == null || matterCode1.isEmpty()) {
            throw new MissingDataException("No matter code 1 provided");
        }
        if (matterCode2 == null || matterCode2.isEmpty()) {
            throw new MissingDataException("No matter code 2 provided");
        }
        if (locationCode == null || locationCode.isEmpty()) {
            throw new MissingDataException("No provider location provided");
        }
        if (caseStage == null || caseStage.isEmpty()) {
            throw new MissingDataException("No case stage provided");
        }
    }

}