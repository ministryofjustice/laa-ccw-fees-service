package uk.gov.laa.ccw.mapper.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.model.CaseStage;
import uk.gov.laa.ccw.entity.CaseStagesEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CaseStagesMapperTest {
    @InjectMocks
    private CaseStagesMapper mapper = new CaseStagesMapperImpl();

    @Test
    void shouldMapCaseStageEntityToCaseStage() {
        CaseStagesEntity entity = CaseStagesEntity.builder()
                .caseStageId("cs1")
                .description("description")
                .build();
        CaseStage result = mapper.toCaseStage(entity);

        assertNotNull(result);
        assertEquals(result.getCaseStageId(), entity.getCaseStageId());
    }
}