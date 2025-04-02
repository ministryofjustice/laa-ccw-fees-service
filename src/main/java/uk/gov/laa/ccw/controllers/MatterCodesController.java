package uk.gov.laa.ccw.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccw.mapping.CaseStagesResponseMapping;
import uk.gov.laa.ccw.mapping.MatterCodesResponseMapping;
import uk.gov.laa.ccw.models.CaseStages200Response;
import uk.gov.laa.ccw.models.MatterCodes200Response;
import uk.gov.laa.ccw.services.MatterCodesService;

/**
 * Controller for handling the matter codes requests.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class MatterCodesController {

    private final MatterCodesService service;

    /**
     * Gets all the matter code ones.
     *
     * @return the matter codes
     */
    @GetMapping("/v1/matter-codes")
    public MatterCodes200Response getAllMatterCodeOnes() {
        log.info("retrieve all matter codes");
        return MatterCodes200Response.builder()
                .matterCodes(
                        service.getAllMatterCodes()
                                .stream()
                                .map(MatterCodesResponseMapping::map)
                                .toList())
                .build();
    }

    /**
     * Gets the matter code twos for the given matter code one.
     *
     * @param id the matter code one
     * @return the matter code twos
     */
    @GetMapping("/v1/matter-codes/{id}/matter-code-2")
    public MatterCodes200Response getMatterCodeTwosForMatterCodeOne(@PathVariable(value = "id") String id) {
        log.info("retrieve all matter code twos for matter code {}", id);
        return MatterCodes200Response.builder()
                .matterCodes(
                        service.getAllMatterTwosForMatterCodeOne(id)
                                .stream()
                                .map(MatterCodesResponseMapping::map)
                                .toList())
                .build();
    }
}
