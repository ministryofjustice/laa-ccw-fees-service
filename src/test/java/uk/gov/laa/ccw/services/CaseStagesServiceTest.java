package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.laa.ccw.dao.CaseStagesDao;
import uk.gov.laa.ccw.models.CaseStage;
import uk.gov.laa.ccw.models.MatterCode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest()
public class CaseStagesServiceTest {
    @Mock
    private CaseStagesDao caseStagesDao;

    @InjectMocks
    private CaseStagesService classUnderTest;

    private List<CaseStage> setupData() {
        List<CaseStage> dataSet = new ArrayList<CaseStage>();
        CaseStage rowSet = CaseStage.builder()
                .caseStageId("cs1")
                .build();
        dataSet.add(rowSet);
        rowSet = CaseStage.builder()
                .caseStageId("cs2")
                .build();
        dataSet.add(rowSet);
        return dataSet;
    }

    @Test
    void shouldFetchCaseStagesForSpecificMatterCodeOne() {

        when(caseStagesDao.fetchCaseStages(anyString()))
                .thenReturn(setupData());

        List<CaseStage> dataReturned = classUnderTest.getAllCaseStagesForMatterCodeOne("CODE1");
        assertEquals(2, dataReturned.size());
        assertEquals("cs1", dataReturned.get(0).getCaseStageId());
        assertEquals("cs2", dataReturned.get(1).getCaseStageId());
    }

}
