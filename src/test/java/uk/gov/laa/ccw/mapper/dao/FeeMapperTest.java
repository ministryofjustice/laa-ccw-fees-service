package uk.gov.laa.ccw.mapper.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.FeesEntity;
import uk.gov.laa.ccw.model.FixedFee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FeeMapperTest {

    @InjectMocks
    private FeeMapper mapper = new FeeMapperImpl();

    @Test
    void shouldMapFeeEntityToFixedFee() {
        FeesEntity entity = FeesEntity.builder()
                .amount(12.34)
                .build();
        FixedFee result = mapper.toFee(entity);

        assertNotNull(result);
        assertEquals(result.getAmount(), entity.getAmount());
    }
}
