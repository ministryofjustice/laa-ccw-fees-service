package uk.gov.laa.ccw.services.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.models.api.CaseStageRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaseStageValidator {
    public void validateRequest(CaseStageRequest request) throws MissingDataException {
        if (request.getMatterCode1() == null || request.getMatterCode1().isEmpty()) {
            throw new MissingDataException("No matter code 1 provided");
        }

        if (request.getMatterCode2() == null || request.getMatterCode2().isEmpty()) {
            throw new MissingDataException("No matter code 2 provided");
        }
    }
}
