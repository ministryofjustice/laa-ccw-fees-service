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

@Slf4j
@RequiredArgsConstructor
@RestController
public class FeesController {
    private final FeesService service;

    @GetMapping("/v1/fees/calculate")
    public Fee200Response getFees(@RequestBody FeeRequest request) {
        log.info("calculating fees");
        Fee result = service.calculateFees(request.getLocationCode(), request.getCaseStage());
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
