package uk.gov.laa.ccw.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.mapper.api.CaseStagesResponseMapper;
import uk.gov.laa.ccw.models.api.CaseStageRequest;
import uk.gov.laa.ccw.models.api.CaseStages200Response;
import uk.gov.laa.ccw.services.CaseStagesService;

/**
 * Controller for handling the case stages requests.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class CaseStagesController {
    private final CaseStagesService caseService;

    private final CaseStagesResponseMapper mapper;

    /**
     * Gets the case stages for the given request.
     *
     * @param request the request
     * @return the case stages
     */
    @GetMapping("/v1/case-stages")
    public CaseStages200Response getCaseStagesForMatterCodes(@RequestBody CaseStageRequest request) {

        validateRequest(request);

        log.info("retrieve all case stages for matter code 1 {} and matter code 2 {}",
                request.getMatterCode1(), request.getMatterCode2());

        return CaseStages200Response.builder()
                .caseStages(
                    caseService.getAllCaseStagesForMatterCodes(request.getMatterCode1(), request.getMatterCode2())
                        .stream()
                        .map(mapper::toCaseStages200ResponseCaseStage)
                        .toList())
                .build();

    }

    private void validateRequest(CaseStageRequest request) throws MissingDataException {
        if (request.getMatterCode1() == null) {
            throw new MissingDataException("No matter code 1 provided");
        }

        if (request.getMatterCode1().isEmpty()) {
            throw new MissingDataException("Matter code 1 cannot be blank");
        }

        if (request.getMatterCode2() == null) {
            throw new MissingDataException("No matter code 2 provided");
        }

        if (request.getMatterCode2().isEmpty()) {
            throw new MissingDataException("Matter code 2 cannot be blank");
        }
    }
}
