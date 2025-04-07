package uk.gov.laa.ccw.mapper.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.model.VatRate;
import uk.gov.laa.ccw.entity.VatRateEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class VatRateMapperTest {
    @InjectMocks
    private VatRateMapper mapper = new VatRateMapperImpl();

    @Test
    void shouldMapVatRateEntityToVatRate() {
        VatRateEntity entity = VatRateEntity.builder()
                .ratePercentage(12.34)
                .build();
        VatRate result = mapper.toVatRate(entity);

        assertNotNull(result);
        assertEquals(result.getRatePercentage(), entity.getRatePercentage());
    }
}