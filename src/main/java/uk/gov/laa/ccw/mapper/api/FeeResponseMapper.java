package uk.gov.laa.ccw.mapper.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.model.Fee;
import uk.gov.laa.ccw.model.FixedFee;
import uk.gov.laa.ccw.model.api.FeeCalculate200Response;
import uk.gov.laa.ccw.model.api.FeeCalculateRequest;
import uk.gov.laa.ccw.model.api.FeeListAvailable200Response;
import uk.gov.laa.ccw.model.api.FeeListAvailable200ResponseFee;
import uk.gov.laa.ccw.model.api.FeeListAvailableRequest;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Maps data into a fee response api.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FeeResponseMapper {
    /**
     * Converts from Fee class and FeeCalculateRequest into a fee calculate response.
     */
    public FeeCalculate200Response toFeeCalculateResponse(Fee fee, FeeCalculateRequest feeRequest) {
        DecimalFormat df = new DecimalFormat("#0.00");

        return FeeCalculate200Response.builder()
                .matterCode1(feeRequest.getMatterCode1())
                .matterCode2(feeRequest.getMatterCode2())
                .caseStage(feeRequest.getCaseStage())
                .locationCode(feeRequest.getLocationCode())
                .amount(df.format(fee.getAmount()))
                .vat(df.format(fee.getVat()))
                .total(df.format(fee.getTotal()))
                .build();
    }

    /**
     * Maps the given fees and fees list available to a FeeListAvailable200Response.
     *
     * @param fees the fee
     * @param feeRequest the fee list available request
     * @return the FeeCalculate200Response
     */
    public FeeListAvailable200Response toListAvailableResponse(List<FixedFee> fees,
                                                                         FeeListAvailableRequest feeRequest) {
        DecimalFormat df = new DecimalFormat("#0.00");

        return FeeListAvailable200Response.builder()
                .matterCode1(feeRequest.getMatterCode1())
                .matterCode2(feeRequest.getMatterCode2())
                .caseStage(feeRequest.getCaseStage())
                .locationCode(feeRequest.getLocationCode())
                .fees(fees.stream()
                        .map(f ->
                                FeeListAvailable200ResponseFee
                                        .builder()
                                        .description(f.getDescription())
                                        .amount(df.format(f.getAmount()))
                                        .levelCode(f.getLevelCode())
                                        .type(f.getLevelCodeType())
                                        .build()
                        )
                        .toList())
                .build();
    }

}