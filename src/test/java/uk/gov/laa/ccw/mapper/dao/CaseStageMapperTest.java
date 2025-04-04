package uk.gov.laa.ccw.mapper.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.CaseStagesEntity;
import uk.gov.laa.ccw.models.CaseStage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CaseStageMapperTest {

    private final CaseStageMapper caseStageMapper = new CaseStageMapperImpl();

    @Test
    void shouldMapToCaseStage() {
        CaseStagesEntity caseStagesEntity = CaseStagesEntity.builder()
                .caseStageId("cs1")
                .build();

        CaseStage result = caseStageMapper.toCaseStage(caseStagesEntity);

        assertNotNull(result);
        assertEquals("cs1", result.getCaseStageId());
        assertEquals("", result.getDescription());
    }
}