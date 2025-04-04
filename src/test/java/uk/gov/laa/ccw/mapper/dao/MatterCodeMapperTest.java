package uk.gov.laa.ccw.mapper.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.MatterCodesOneEntity;
import uk.gov.laa.ccw.entity.MatterCodesTwoEntity;
import uk.gov.laa.ccw.models.MatterCode;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MatterCodeMapperTest {

    @InjectMocks
    private MatterCodeMapper matterCodeMapper = new MatterCodeMapperImpl();

    @Test
    void shouldMapMatterCodesOneEntityToMatterCode() {
        MatterCodesOneEntity matterCodesOneEntity = MatterCodesOneEntity.builder()
                .matterCodeId("mt1")
                .build();

        MatterCode result = matterCodeMapper.toMatterCode(matterCodesOneEntity);

        assertNotNull(result);
        assertEquals("mt1", result.getMatterCodeId(), matterCodesOneEntity.getMatterCodeId());
        assertEquals("", result.getDescription(), matterCodesOneEntity.getDescription());
    }

    @Test
    void shouldMapMatterCodesTwoEntityToMatterCode() {
        MatterCodesTwoEntity matterCodesTwoEntity = MatterCodesTwoEntity.builder()
                .matterCodeId("mt2")
                .build();

        MatterCode result = matterCodeMapper.toMatterCode(matterCodesTwoEntity);

        assertNotNull(result);
        assertEquals("mt2", result.getMatterCodeId());
        assertEquals("", result.getDescription());
    }

}