package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.CaseStagesCombinationsEntity;
import uk.gov.laa.ccw.exceptions.CaseStagesNotFoundException;
import uk.gov.laa.ccw.mapper.dao.CaseStagesMapper;
import uk.gov.laa.ccw.model.CaseStage;
import uk.gov.laa.ccw.repository.CaseStagesCombinationsRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CaseStagesServiceTest {
    @Mock
    private CaseStagesCombinationsRepository caseStagesCombinationsRepository;

    @Mock
    private CaseStagesMapper caseStagesMapper;

    @InjectMocks
    private CaseStagesService classUnderTest;

    @Test
    void shouldFetchCaseStagesForMatterCodeOneAndMatterCodeTwo() {

        String matterCodeOne = "CODE1";
        String matterCodeTwo = "CODE2";

        setUpMockData(matterCodeOne, false);

        List<CaseStage> dataReturned = classUnderTest.getAllCaseStagesForMatterCodes(matterCodeOne, matterCodeTwo);
        assertEquals(2, dataReturned.size());
        assertEquals("cs1", dataReturned.get(0).getCaseStageId());
        assertEquals("cs2", dataReturned.get(1).getCaseStageId());
    }

    @Test
    void shouldFetchCaseStagesForMatterCodeOneAndMatterCodeTwoWithFPL10() {

        String matterCodeOne = "CODE1";
        String matterCodeTwo = "CODE2";

        setUpMockData(matterCodeOne, true);

        List<CaseStage> dataReturned = classUnderTest.getAllCaseStagesForMatterCodes(matterCodeOne, matterCodeTwo);
        assertEquals(2, dataReturned.size());
        assertEquals("cs1", dataReturned.get(0).getCaseStageId());
        assertEquals("cs2", dataReturned.get(1).getCaseStageId());
    }

    @Test
    void shouldFetchCaseStagesForFAMA() {

        String matterCodeOne = "FAMA";
        String matterCodeTwo = "CODE2";

        setUpMockData(matterCodeOne, true);

        List<CaseStage> dataReturned = classUnderTest.getAllCaseStagesForMatterCodes(matterCodeOne, matterCodeTwo);
        assertEquals(2, dataReturned.size());
        assertEquals("cs1", dataReturned.get(0).getCaseStageId());
        assertEquals("cs2", dataReturned.get(1).getCaseStageId());
    }

    @Test
    void shouldFetchCaseStagesForFPET() {

        String matterCodeOne = "CODE1";
        String matterCodeTwo = "FPET";

        setUpMockData(matterCodeOne, true);

        List<CaseStage> dataReturned = classUnderTest.getAllCaseStagesForMatterCodes(matterCodeOne, matterCodeTwo);
        assertEquals(2, dataReturned.size());
        assertEquals("cs1", dataReturned.get(0).getCaseStageId());
        assertEquals("cs2", dataReturned.get(1).getCaseStageId());
    }

    @Test
    void shouldFetchCaseStagesForFAMAAndFPET() {

        String matterCodeOne = "FAMA";
        String matterCodeTwo = "FPET";

        setUpMockData(matterCodeOne, true);

        List<CaseStage> dataReturned = classUnderTest.getAllCaseStagesForMatterCodes(matterCodeOne, matterCodeTwo);
        assertEquals(3, dataReturned.size());
        assertEquals("cs1", dataReturned.get(0).getCaseStageId());
        assertEquals("cs2", dataReturned.get(1).getCaseStageId());
        assertEquals("FPL10", dataReturned.get(2).getCaseStageId());
    }

    @Test
    void shouldThrowExceptionWhenCaseStagesNotFound() {

        String matterCodeOne = "CODE1";
        String matterCodeTwo = "CODE2";

        when(caseStagesCombinationsRepository
                .findByMatterCodeOne(matterCodeOne)).thenReturn(new ArrayList<>());

        assertThrows(CaseStagesNotFoundException.class,
                () -> classUnderTest.getAllCaseStagesForMatterCodes(matterCodeOne, matterCodeTwo));
    }

    private void setUpMockData(String matterCodeOne, boolean addInFPL10) {
        CaseStagesCombinationsEntity cs1Entity = CaseStagesCombinationsEntity.builder().caseStageId("cs1").build();
        CaseStagesCombinationsEntity cs2Entity = CaseStagesCombinationsEntity.builder().caseStageId("cs2").build();
        List<CaseStagesCombinationsEntity> caseStagesEntities = new ArrayList<>();
        caseStagesEntities.add(cs1Entity);
        caseStagesEntities.add(cs2Entity);

        when(caseStagesMapper.toCaseStage(cs1Entity)).thenReturn(CaseStage.builder().caseStageId("cs1").build());
        when(caseStagesMapper.toCaseStage(cs2Entity)).thenReturn(CaseStage.builder().caseStageId("cs2").build());

        if (addInFPL10) {
            CaseStagesCombinationsEntity fpl10Entity = CaseStagesCombinationsEntity.builder().caseStageId("FPL10").build();
            caseStagesEntities.add(fpl10Entity);
            when(caseStagesMapper.toCaseStage(fpl10Entity)).thenReturn(CaseStage.builder().caseStageId("FPL10").build());
        }

        when(caseStagesCombinationsRepository.findByMatterCodeOne(matterCodeOne)).thenReturn(caseStagesEntities);
    }

}