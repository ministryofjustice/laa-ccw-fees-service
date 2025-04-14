package uk.gov.laa.ccw.mapper.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.model.FeeTotals;
import uk.gov.laa.ccw.model.FeeDetails;
import uk.gov.laa.ccw.model.api.*;

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
    public FeeCalculate200Response toFeeCalculateResponse(FeeTotals fee) {
        DecimalFormat df = new DecimalFormat("#0.00");

        FeeCalculate200ResponseFee responseFee = FeeCalculate200ResponseFee.builder()
                .amount(df.format(fee.getAmount()))
                .vat(df.format(fee.getVat()))
                .total(df.format(fee.getTotal()))
                .build();
        return FeeCalculate200Response.builder()
                .totals(responseFee)
                .build();
    }

    /**
     * Maps the given fees and fees list available to a FeeListAvailable200Response.
     *
     * @param fees the fee
     * @return the FeeCalculate200Response
     */
    public FeeListAvailable200Response toListAvailableResponse(List<FeeDetails> fees) {
        DecimalFormat df = new DecimalFormat("#0.00");

        return FeeListAvailable200Response.builder()
                .fees(fees.stream()
                        .map(f ->
                                FeeListAvailable200ResponseFee
                                        .builder()
                                        .description(f.getDescription())
                                        .amount(df.format(f.getAmount()))
                                        .levelCode(f.getLevelCode())
                                        .levelCodeType(f.getLevelCodeType())
                                        .formQuestion(f.getFormQuestion())
                                        .build()
                        )
                        .toList())
                .build();
    }

}