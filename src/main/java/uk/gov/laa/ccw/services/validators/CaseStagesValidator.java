package uk.gov.laa.ccw.services.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.model.api.CaseStagesRequest;

/**
 * Validator class for case stages request.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CaseStagesValidator {

    /**
     * Validates the case stage request.
     *
     * @param request the case stage request
     */
    public void validateRequest(CaseStagesRequest request) throws MissingDataException {
        if (request.getMatterCode1() == null || request.getMatterCode1().isEmpty()) {
            throw new MissingDataException("No matter code 1 provided");
        }

        if (request.getMatterCode2() == null || request.getMatterCode2().isEmpty()) {
            throw new MissingDataException("No matter code 2 provided");
        }
    }
}
