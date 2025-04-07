package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.dao.CaseStagesDao;
import uk.gov.laa.ccw.model.CaseStage;
import uk.gov.laa.ccw.exceptions.CaseStagesNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CaseStagesServiceTest {
    @Mock
    private CaseStagesDao caseStagesDao;

    @InjectMocks
    private CaseStagesService classUnderTest;

    private List<CaseStage> setupData(Boolean addInFPL10) {
        List<CaseStage> dataSet = new ArrayList<CaseStage>();
        CaseStage rowSet;
        if (addInFPL10) {
            rowSet = CaseStage.builder()
                    .caseStageId("FPL10")
                    .build();
            dataSet.add(rowSet);
        }
        rowSet = CaseStage.builder()
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
    void shouldFetchCaseStagesForMatterCodeOneAndMatterCodeTwo() {

        when(caseStagesDao.fetchCaseStages(anyString()))
                .thenReturn(setupData(false));

        List<CaseStage> dataReturned = classUnderTest.getAllCaseStagesForMatterCodes("CODE1", "CODE2");
        assertEquals(2, dataReturned.size());
        assertEquals("cs1", dataReturned.get(0).getCaseStageId());
        assertEquals("cs2", dataReturned.get(1).getCaseStageId());
    }

    @Test
    void shouldFetchCaseStagesForMatterCodeOneAndMatterCodeTwoWithFPL10() {

        when(caseStagesDao.fetchCaseStages(anyString()))
                .thenReturn(setupData(true));

        List<CaseStage> dataReturned = classUnderTest.getAllCaseStagesForMatterCodes("CODE1", "CODE2");
        assertEquals(2, dataReturned.size());
        assertEquals("cs1", dataReturned.get(0).getCaseStageId());
        assertEquals("cs2", dataReturned.get(1).getCaseStageId());
    }

    @Test
    void shouldFetchCaseStagesForFAMA() {

        when(caseStagesDao.fetchCaseStages(anyString()))
                .thenReturn(setupData(true));

        List<CaseStage> dataReturned = classUnderTest.getAllCaseStagesForMatterCodes("FAMA", "CODE2");
        assertEquals(2, dataReturned.size());
        assertEquals("cs1", dataReturned.get(0).getCaseStageId());
        assertEquals("cs2", dataReturned.get(1).getCaseStageId());
    }

    @Test
    void shouldFetchCaseStagesForFPET() {

        when(caseStagesDao.fetchCaseStages(anyString()))
                .thenReturn(setupData(true));

        List<CaseStage> dataReturned = classUnderTest.getAllCaseStagesForMatterCodes("CODE1", "FPET");
        assertEquals(2, dataReturned.size());
        assertEquals("cs1", dataReturned.get(0).getCaseStageId());
        assertEquals("cs2", dataReturned.get(1).getCaseStageId());
    }

    @Test
    void shouldFetchCaseStagesForFAMAAndFPET() {

        when(caseStagesDao.fetchCaseStages(anyString()))
                .thenReturn(setupData(true));

        List<CaseStage> dataReturned = classUnderTest.getAllCaseStagesForMatterCodes("FAMA", "FPET");
        assertEquals(3, dataReturned.size());
        assertEquals("FPL10", dataReturned.get(0).getCaseStageId());
    }

    @Test
    void shouldThrowExceptionWhenDaoThrowsDatabaseReadException() {

        doThrow(new CaseStagesNotFoundException(""){}).when(caseStagesDao).fetchCaseStages(anyString());

        assertThrows(CaseStagesNotFoundException.class,
                () -> classUnderTest.getAllCaseStagesForMatterCodes("CODE1", "CODE2"));
    }
}