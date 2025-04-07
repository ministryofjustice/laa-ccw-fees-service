package uk.gov.laa.ccw.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccw.mapper.api.CaseStagesResponseMapper;
import uk.gov.laa.ccw.model.api.CaseStages200Response;
import uk.gov.laa.ccw.model.api.CaseStagesRequest;
import uk.gov.laa.ccw.services.CaseStagesService;
import uk.gov.laa.ccw.services.validators.CaseStagesValidator;

/**
 * Controller for handling the case stages requests.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class CaseStagesController {
    private final CaseStagesService caseService;
    private final CaseStagesValidator caseValidatorService;
    private final CaseStagesResponseMapper caseStagesResponseMapper;

    /**
     * Gets the case stages for the given request.
     *
     * @param request the request
     * @return the case stages
     */
    @GetMapping("/v1/case-stages")
    public CaseStages200Response getCaseStagesForMatterCodes(@RequestBody CaseStagesRequest request) {

        caseValidatorService.validateRequest(request);

        log.info("retrieve all case stages for matter code 1 {} and matter code 2 {}",
                request.getMatterCode1(), request.getMatterCode2());

        return CaseStages200Response.builder()
                .caseStages(
                        caseService.getAllCaseStagesForMatterCodes(request.getMatterCode1(), request.getMatterCode2())
                                .stream()
                                .map(caseStagesResponseMapper::toCaseStages200ResponseCaseStage)
                                .toList())
                .build();

    }
}