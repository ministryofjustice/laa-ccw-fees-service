package uk.gov.laa.ccw.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.model.CaseStage;
import uk.gov.laa.ccw.entity.CaseStagesEntity;
import uk.gov.laa.ccw.exceptions.CaseStagesNotFoundException;
import uk.gov.laa.ccw.mapper.dao.CaseStagesMapper;
import uk.gov.laa.ccw.repository.CaseStagesRepository;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CaseStagesDaoTest {
    @Mock
    private CaseStagesRepository repository;

    @Mock
    private CaseStagesMapper mapper;

    @InjectMocks
    private CaseStagesDao classUnderTest;

    @Test
    void shouldFetchCaseStagesForOneMatterCode() {

        when(repository.findCaseStagesByMatterCodeOne(anyString()))
                .thenReturn(List.of(
                        CaseStagesEntity.builder()
                                .caseStageId("cs1")
                                .description("desc1")
                                .build(),
                        CaseStagesEntity.builder()
                                .caseStageId("cs2")
                                .description("desc2")
                                .build()
                ));

        when(mapper.toCaseStage(any()))
                .thenReturn(CaseStage.builder()
                                .caseStageId("cs1")
                                .description("desc1")
                                .build(),
                        CaseStage.builder()
                                .caseStageId("cs2")
                                .description("desc2")
                                .build()
                );

        List<CaseStage> dataReturned = classUnderTest.fetchCaseStages("CODE1");
        assertEquals(2, dataReturned.size());
        assertEquals("cs1", dataReturned.get(0).getCaseStageId());
        assertEquals("desc2", dataReturned.get(1).getDescription());
    }

    @Test
    void shouldThrowExceptionIfCaseStagesNotReadCorrectly() {

        doThrow(new CaseStagesNotFoundException(""){}).when(repository).findCaseStagesByMatterCodeOne(anyString());

        assertThrows(CaseStagesNotFoundException.class,
                () -> classUnderTest.fetchCaseStages("CODE1"));
    }
}