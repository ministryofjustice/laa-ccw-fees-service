package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.CaseStagesEntity;
import uk.gov.laa.ccw.mapper.dao.CaseStagesMapper;
import uk.gov.laa.ccw.models.CaseStage;
import uk.gov.laa.ccw.repository.CaseStagesRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CaseStagesServiceTest {

    @Mock
    private CaseStagesRepository caseStagesRepository;

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

    private void setUpMockData(String matterCodeOne, boolean addInFPL10) {
        CaseStagesEntity cs1Entity = CaseStagesEntity.builder().caseStageId("cs1").build();
        CaseStagesEntity cs2Entity = CaseStagesEntity.builder().caseStageId("cs2").build();
        List<CaseStagesEntity> caseStagesEntities = new ArrayList<>();
        caseStagesEntities.add(cs1Entity);
        caseStagesEntities.add(cs2Entity);

        when(caseStagesMapper.toCaseStage(cs1Entity)).thenReturn(CaseStage.builder().caseStageId("cs1").build());
        when(caseStagesMapper.toCaseStage(cs2Entity)).thenReturn(CaseStage.builder().caseStageId("cs2").build());

        if (addInFPL10) {
            CaseStagesEntity fpl10Entity = CaseStagesEntity.builder().caseStageId("FPL10").build();
            caseStagesEntities.add(fpl10Entity);
            when(caseStagesMapper.toCaseStage(fpl10Entity)).thenReturn(CaseStage.builder().caseStageId("FPL10").build());
        }

        when(caseStagesRepository.findCaseStagesByMatterCodeOne(matterCodeOne)).thenReturn(caseStagesEntities);
    }

}
