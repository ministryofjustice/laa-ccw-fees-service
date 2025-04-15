package uk.gov.laa.ccw.mapper.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.model.FeeDetails;
import uk.gov.laa.ccw.model.FeeElement;
import uk.gov.laa.ccw.model.api.FeeCalculate200ResponseFee;
import uk.gov.laa.ccw.model.api.FeeListAvailable200ResponseFee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FeeResponseMapperTest {

    private final FeeResponseMapper feeRespMapper = new FeeResponseMapperImpl();

    @Test
    void shouldMapToFeeCalculationResponse() {
        FeeElement fee = FeeElement.builder()
                .amount("12.34")
                .vat("2.56")
                .total("233.45")
                .build();

        FeeCalculate200ResponseFee result = feeRespMapper.toFeeCalculateResponse(fee);

        assertNotNull(result);
        assertEquals("12.34", result.getAmount());
        assertEquals("2.56", result.getVat());
    }

    @Test
    void shouldMapToFeeDetailsResponse() {
        FeeDetails fee = FeeDetails.builder()
                .formQuestion("how")
                .amount(99.0)
                .build();

        FeeListAvailable200ResponseFee result = feeRespMapper.toListAvailableResponse(fee);

        assertNotNull(result);
        assertEquals(99.0, result.getAmount());
        assertEquals("how", result.getFormQuestion());
    }

}
