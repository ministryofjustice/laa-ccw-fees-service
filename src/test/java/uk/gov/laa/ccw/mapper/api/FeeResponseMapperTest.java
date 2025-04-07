package uk.gov.laa.ccw.mapper.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.model.Fee;
import uk.gov.laa.ccw.model.api.FeeCalculate200Response;
import uk.gov.laa.ccw.model.api.FeeCalculateRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FeeResponseMapperTest {

    @InjectMocks
    private FeeResponseMapper classUnderTest;

    @Test
    void shouldMapToFeeResponse() {
        Fee fee = Fee.builder()
                .amount(12.34)
                .vat(2.56)
                .total(233.45)
                .build();
        FeeCalculateRequest feeRequest = FeeCalculateRequest.builder()
                .caseStage("caseStage")
                .matterCode1("matterCode1")
                .matterCode2("matterCode2")
                .locationCode("locationCode")
                .build();

        FeeCalculate200Response result = classUnderTest.toFeeCalculateResponse(
                fee,
                feeRequest);

        assertNotNull(result);
        assertEquals("caseStage", result.getCaseStage());
        assertEquals("matterCode1", result.getMatterCode1());
        assertEquals("12.34", result.getAmount());
        assertEquals("2.56", result.getVat());
    }
}
