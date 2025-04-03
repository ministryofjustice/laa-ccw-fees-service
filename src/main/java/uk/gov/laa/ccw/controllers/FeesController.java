package uk.gov.laa.ccw.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.api.Fee200Response;
import uk.gov.laa.ccw.models.api.FeeRequest;
import uk.gov.laa.ccw.services.FeesService;
import uk.gov.laa.ccw.services.validators.FeesValidator;

import java.util.ArrayList;

/**
 * Controller for handling the fees requests.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class FeesController {
    private final FeesService service;
    private final FeesValidator validator;
    /**
     * Calculates the fee for a given request.
     *
     * @param request the fee request
     * @return the fee
     */
    @GetMapping("/v1/fees/calculate")
    public Fee200Response getFees(@RequestBody FeeRequest request) {

        if (request.getLevelCodes() == null) {
            request.setLevelCodes(new ArrayList<>());
        }

        validator.validateRequest(request);

        log.info("calculating fees");
        Fee result = service.calculateFees(
                request.getLocationCode(),
                request.getCaseStage(),
                request.getLevelCodes());

        return Fee200Response.builder()
                .locationCode(request.getLocationCode())
                .matterCode1(request.getMatterCode1())
                .matterCode2(request.getMatterCode2())
                .caseStage(request.getCaseStage())
                .amount(result.getAmount())
                .vat(result.getVat())
                .total(result.getTotal())
                .build();
    }
}
