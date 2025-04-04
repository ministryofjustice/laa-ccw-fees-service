package uk.gov.laa.ccw.mapping.api;

import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.FeeRecord;
import uk.gov.laa.ccw.models.api.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeeResponseMapping {
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

    public static FeeListAvailable200Response mapToListAvailableResponse(List<FeeRecord> fees, FeeListAvailableRequest feeRequest) {
        DecimalFormat df = new DecimalFormat("#0.00");

        return FeeListAvailable200Response.builder()
                .matterCode1(feeRequest.getMatterCode1())
                .matterCode2(feeRequest.getMatterCode2())
                .caseStage(feeRequest.getCaseStage())
                .locationCode(feeRequest.getLocationCode())
                .fees(  fees.stream()
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
