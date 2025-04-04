package uk.gov.laa.ccw.mapping.api;

import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.api.FeeCalculate200Response;
import uk.gov.laa.ccw.models.api.FeeCalculateRequest;

import java.text.DecimalFormat;

public class FeeResponseMapping {
    public static FeeCalculate200Response map(Fee fee, FeeCalculateRequest feeRequest) {
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
}
