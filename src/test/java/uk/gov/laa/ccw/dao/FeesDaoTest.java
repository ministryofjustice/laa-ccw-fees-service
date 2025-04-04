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
import uk.gov.laa.ccw.models.FeeRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeesDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private FeesDao classUnderTest;

    @Test
    void shouldFetchFeesForLocation() {

        when(jdbcTemplate.queryForList(anyString(), anyString(), anyString()))
                .thenReturn(createFeesTestData());

        List<FeeRecord> dataReturned = classUnderTest.fetchFeesForLocationAndCaseStage(
                "LOC1", "CS1");

        assertEquals(4, dataReturned.size());
    }

    @Test
    void shouldThrowExceptionIfFeesNotReadCorrectly() {

        doThrow(new DataAccessException(""){})
                .when(jdbcTemplate).queryForList(anyString(), anyString(), anyString());

        assertThrows(DatabaseReadException.class,
                () -> classUnderTest.fetchFeesForLocationAndCaseStage("LOC1", "CS2"));
    }

    private List<Map<String, Object>> createFeesTestData() {
        List<Map<String, Object>> dataSet = new ArrayList<Map<String, Object>>();
        Map<String, Object> rowSet = new HashMap<String, Object>();
        rowSet.put("AMOUNT", "16.0");
        rowSet.put("LEVEL_CODE", "LV1");
        rowSet.put("TYPE", "A");
        rowSet.put("DESCRIPTION", "LEVEL 1");
        dataSet.add(rowSet);
        rowSet = new HashMap<String, Object>();
        rowSet.put("AMOUNT", "32.0");
        rowSet.put("LEVEL_CODE", "LV2");
        rowSet.put("TYPE", "A");
        rowSet.put("DESCRIPTION", "LEVEL 2");
        dataSet.add(rowSet);
        rowSet = new HashMap<String, Object>();
        rowSet.put("AMOUNT", "64.0");
        rowSet.put("LEVEL_CODE", "LV3");
        rowSet.put("TYPE", "O");
        rowSet.put("DESCRIPTION", "LEVEL 3");
        dataSet.add(rowSet);
        rowSet = new HashMap<String, Object>();
        rowSet.put("AMOUNT", "128.0");
        rowSet.put("LEVEL_CODE", "LV4");
        rowSet.put("TYPE", "OM");
        rowSet.put("DESCRIPTION", "LEVEL 4");
        dataSet.add(rowSet);
        return dataSet;
    }

}
