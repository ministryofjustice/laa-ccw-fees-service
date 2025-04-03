package uk.gov.laa.ccw.mapper.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.MatterCodesOneEntity;
import uk.gov.laa.ccw.models.MatterCode;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MatterCodeMapperTest {

    @InjectMocks
    private MatterCodeMapper matterCodeMapper = new MatterCodeMapperImpl();

    @Test
    void shouldMapToItemEntity() {
        MatterCodesOneEntity matterCodesOneEntity = MatterCodesOneEntity.builder()
                .matterCodeId("mt1")
                .build();
        MatterCode result = matterCodeMapper.toMatterCode(matterCodesOneEntity);

        assertNotNull(result);
        assertEquals("mt1", result.getMatterCodeId(), matterCodesOneEntity.getMatterCodeId());
        assertEquals("", result.getDescription(), matterCodesOneEntity.getDescription());
    }

}