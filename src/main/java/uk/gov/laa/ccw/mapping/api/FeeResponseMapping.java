package uk.gov.laa.ccw.mapping.api;

import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.FeeRecord;
import uk.gov.laa.ccw.models.api.FeeCalculate200Response;
import uk.gov.laa.ccw.models.api.FeeCalculateRequest;
import uk.gov.laa.ccw.models.api.FeeListAvailable200Response;
import uk.gov.laa.ccw.models.api.FeeListAvailable200ResponseFee;
import uk.gov.laa.ccw.models.api.FeeListAvailableRequest;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Mapping class between Fee and FeeCalculate200Response.
 */
public class FeeResponseMapping {

    /**
     * Maps the given fee calculate request to a MatterCodes200ResponseMatterCode.
     *
     * @param fee the fee
     * @param feeRequest the fee request
     * @return the FeeCalculate200Response
     */
    public static FeeCalculate200Response mapToFeeCalculateResponse(Fee fee, FeeCalculateRequest feeRequest) {
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
    public static FeeListAvailable200Response mapToListAvailableResponse(List<FeeRecord> fees,
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
