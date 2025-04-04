package uk.gov.laa.ccw.mapper.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.FeesEntity;
import uk.gov.laa.ccw.models.FeeRecord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FeeRecordMapperTest {

    private FeeRecordMapper feeRecordMapper = new FeeRecordMapperImpl();

    @Test
    void shouldMap() {
        FeesEntity feesEntity = FeesEntity.builder()
                .caseStage("CS1")
                .levelCode("L1")
                .providerLocation("LON")
                .amount(100.00)
                .build();

        FeeRecord result = feeRecordMapper.toFeeRecord(feesEntity);

        assertNotNull(result);
        assertEquals("CS1", result.getCaseStage());
        assertEquals("L1", result.getLevelCode());
        assertEquals("LON", result.getProviderLocation());
        assertEquals(100.00, result.getAmount());
    }
}