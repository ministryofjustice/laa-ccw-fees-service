package uk.gov.laa.ccw.services.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.exceptions.MissingDataException;

/**
 * Validator class for the matter codes.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MatterCodesValidator {

    /**
     * Validates the matter codes request.
     *
     * @param id the matter code id
     */
    public void validateRequest(String id) throws MissingDataException {
        if (id == null || id.isEmpty()) {
            throw new MissingDataException("No law type provided");
        }

    }
}