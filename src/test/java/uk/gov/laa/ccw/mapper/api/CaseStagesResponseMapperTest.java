package uk.gov.laa.ccw.mapper.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.model.CaseStage;
import uk.gov.laa.ccw.model.api.CaseStages200ResponseCaseStage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CaseStagesResponseMapperTest {

    private final CaseStagesResponseMapper caseStagesResponseMapper = new CaseStagesResponseMapperImpl();

    @Test
    void shouldMapToCaseStagesResponse() {
        CaseStage caseStage = CaseStage.builder().caseStageId("cs1").build();

        CaseStages200ResponseCaseStage result = caseStagesResponseMapper.toCaseStages200ResponseCaseStage(caseStage);

        assertNotNull(result);
        assertEquals("cs1", result.getCaseStage());
        assertEquals("", result.getDescription());
    }
}