package uk.gov.laa.ccw.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uk.gov.laa.ccw.mapping.CaseStagesResponseMapping;
import uk.gov.laa.ccw.models.CaseStages200Response;
import uk.gov.laa.ccw.services.CaseStagesService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CaseStagesController {
    private final CaseStagesService caseService;

    @GetMapping("/v1/case-stages/{mattercode1}/{mattercode2}")
    public CaseStages200Response getCaseStagesForMatterCodes(@PathVariable(value = "mattercode1") String mattercode1, @PathVariable(value = "mattercode2") String mattercode2) {
        log.info("retrieve all case stages for matter code 1 {} and matter code 2 {}", mattercode1, mattercode2);
        return CaseStages200Response.builder()
                .caseStages(
                        caseService.getAllCaseStagesForMatterCodes(mattercode1, mattercode2)
                                .stream()
                                .map(CaseStagesResponseMapping::map)
                                .toList())
                .build();
    }
}
