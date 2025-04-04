package uk.gov.laa.ccw.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.models.CaseStage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CaseStagesDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CaseStagesDao classUnderTest;

    private List<Map<String, Object>> setupData() {
        List<Map<String, Object>> dataSet = new ArrayList<Map<String, Object>>();
        Map<String, Object> rowSet = new HashMap<String, Object>();
        rowSet.put("CASE_STAGE_ID", "cs1");
        rowSet.put("DESCRIPTION", "desc1");
        dataSet.add(rowSet);
        rowSet = new HashMap<String, Object>();
        rowSet.put("CASE_STAGE_ID", "cs2");
        dataSet.add(rowSet);
        return dataSet;
    }

    @Test
    void shouldFetchCaseStagesForOneMatterCode() {

        when(jdbcTemplate.queryForList(anyString(), anyString()))
                .thenReturn(setupData());

        List<CaseStage> dataReturned = classUnderTest.fetchCaseStages("CODE1");
        assertEquals(2, dataReturned.size());
        assertEquals("cs1", dataReturned.get(0).getCaseStageId());
        assertEquals("", dataReturned.get(1).getDescription());
    }

    @Test
    void shouldThrowExceptionIfCaseStagesNotReadCorrectly() {

        doThrow(new DataAccessException(""){}).when(jdbcTemplate).queryForList(anyString(), anyString());

        assertThrows(DatabaseReadException.class,
                () -> classUnderTest.fetchCaseStages("CODE2"));
    }

    @Test
    void shouldThrowExceptionIfMatterCode1NotFound() {

        doThrow(new MatterCodeNotFoundException(""){}).when(jdbcTemplate).queryForList(anyString(), anyString());

        assertThrows(MatterCodeNotFoundException.class,
                () -> classUnderTest.fetchCaseStages("CODE3"));
    }

}
