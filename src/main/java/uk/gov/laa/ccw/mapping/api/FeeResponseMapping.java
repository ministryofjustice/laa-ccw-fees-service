package uk.gov.laa.ccw.mapping.api;

import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.api.Fee200Response;
import uk.gov.laa.ccw.models.api.FeeRequest;

import java.text.DecimalFormat;

public class FeeResponseMapping {
    public static Fee200Response map(Fee fee, FeeRequest feeRequest) {
        DecimalFormat df = new DecimalFormat("#0.00");

        return Fee200Response.builder()
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
