package uk.gov.laa.ccw.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccw.mapping.api.FeeResponseMapping;
import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.api.FeeCalculate200Response;
import uk.gov.laa.ccw.models.api.FeeCalculateRequest;
import uk.gov.laa.ccw.services.FeesService;
import uk.gov.laa.ccw.services.validators.FeesValidator;

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
    public FeeCalculate200Response getFees(@RequestBody FeeCalculateRequest request) {

        validator.validateRequest(request);

        log.info("calculating fees");
        Fee result = service.calculateFees(
                request.getLocationCode(),
                request.getCaseStage(),
                request.getLevelCodes());

        return FeeResponseMapping.map(result, request);
    }

    @GetMapping("/v1/fees/calculate2")
    public FeeCalculate200Response getListOfFees(@RequestBody FeeCalculateRequest request) {

        validator.validateRequest(request);

        log.info("calculating fees");
        Fee result = service.calculateFees(
                request.getLocationCode(),
                request.getCaseStage(),
                request.getLevelCodes());

        return FeeResponseMapping.map(result, request);
    }
}
