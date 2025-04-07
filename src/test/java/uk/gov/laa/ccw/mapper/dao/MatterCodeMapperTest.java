package uk.gov.laa.ccw.mapper.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.model.MatterCode;
import uk.gov.laa.ccw.entity.MatterCodesOneEntity;
import uk.gov.laa.ccw.entity.MatterCodesTwoEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MatterCodeMapperTest {

    @InjectMocks
    private MatterCodeMapper matterCodeMapper = new MatterCodeMapperImpl();

    @Test
    void shouldMapMatterCodesOneEntityToMatterCode() {
        MatterCodesOneEntity matterCodesOneEntity = MatterCodesOneEntity.builder()
                .matterCodeId("mt1")
                .description("desc")
                .build();
        MatterCode result = matterCodeMapper.toMatterCode(matterCodesOneEntity);

        assertNotNull(result);
        assertEquals("mt1", result.getMatterCodeId(), matterCodesOneEntity.getMatterCodeId());
        assertEquals("desc", result.getDescription(), matterCodesOneEntity.getDescription());
    }

    @Test
    void shouldMapMatterCodesTwoEntityToMatterCode() {
        MatterCodesTwoEntity matterCodesTwoEntity = MatterCodesTwoEntity.builder()
                .matterCodeId("mt2")
                .description("desc")
                .build();
        MatterCode result = matterCodeMapper.toMatterCode(matterCodesTwoEntity);

        assertNotNull(result);
        assertEquals("mt2", result.getMatterCodeId(), matterCodesTwoEntity.getMatterCodeId());
        assertEquals("desc", result.getDescription(), matterCodesTwoEntity.getDescription());
    }

}