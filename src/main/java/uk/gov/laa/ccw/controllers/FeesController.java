package uk.gov.laa.ccw.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccw.mapping.api.FeeResponseMapping;
import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.FeeRecord;
import uk.gov.laa.ccw.models.api.FeeCalculate200Response;
import uk.gov.laa.ccw.models.api.FeeCalculateRequest;
import uk.gov.laa.ccw.models.api.FeeListAvailable200Response;
import uk.gov.laa.ccw.models.api.FeeListAvailableRequest;
import uk.gov.laa.ccw.services.FeesService;
import uk.gov.laa.ccw.services.validators.FeesValidator;

import java.util.List;

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

        validator.validateFeeCalculateRequest(request);

        log.info("calculating fees");
        Fee result = service.calculateFees(
                request.getLocationCode(),
                request.getCaseStage(),
                request.getLevelCodes());

        return FeeResponseMapping.mapToFeeCalculateResponse(result, request);
    }

    /**
     * Get the list of available fees.
     *
     * @param request the fee list available request
     * @return the fee list
     */
    @GetMapping("/v1/fees/list-available")
    public FeeListAvailable200Response getListOfFees(@RequestBody FeeListAvailableRequest request) {

        validator.validateFeeListAvailableRequest(request);

        log.info("get list of fees");
        List<FeeRecord> result = service.getFeesForLocationAndCaseStage(
                request.getLocationCode(),
                request.getCaseStage());

        return FeeResponseMapping.mapToListAvailableResponse(result, request);
    }
}
