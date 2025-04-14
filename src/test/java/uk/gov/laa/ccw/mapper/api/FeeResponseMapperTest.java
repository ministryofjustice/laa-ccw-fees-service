package uk.gov.laa.ccw.mapper.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.model.FeeElement;
import uk.gov.laa.ccw.model.api.FeeCalculate200ResponseFee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FeeResponseMapperTest {

    private final FeeResponseMapper feeRespMapper = new FeeResponseMapperImpl();

    @Test
    void shouldMapToFeeResponse() {
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
}
