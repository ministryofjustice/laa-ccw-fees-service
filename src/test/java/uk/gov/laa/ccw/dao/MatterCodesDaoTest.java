package uk.gov.laa.ccw.dao;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.models.MatterCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest()
public class MatterCodesDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private MatterCodesDao classUnderTest;

    private List<Map<String, Object>> setupMatterCodeQueryDataset() {
        List<Map<String, Object>> dataSet = new ArrayList<Map<String, Object>>();
        Map<String, Object> rowSet = new HashMap<String, Object>();
        rowSet.put("MATTER_CODE_ID", "mt1");
        rowSet.put("DESCRIPTION", "desc1");
        dataSet.add(rowSet);
        rowSet = new HashMap<String, Object>();
        rowSet.put("MATTER_CODE_ID", "mt2");
        dataSet.add(rowSet);
        return dataSet;
    }

    @Test
    void shouldFetchAllMatterCodes() {

        when(jdbcTemplate.queryForList(anyString()))
                .thenReturn(setupMatterCodeQueryDataset());

        List<MatterCode> dataReturned = classUnderTest.fetchAllMatterCodes();
        assertEquals(2, dataReturned.size());
        assertEquals("mt1", dataReturned.get(0).getMatterCodeId());
        assertEquals("", dataReturned.get(1).getDescription());
    }

    @Test
    void shouldThrowExceptionIfMatterCodeNotReadCorrectly() {

        doThrow(new DataAccessException(""){}).when(jdbcTemplate).queryForList(anyString());

        assertThrows(DatabaseReadException.class,
                () -> classUnderTest.fetchAllMatterCodes());
    }

    @Test
    void shouldFetchMatterCode2ForOneMatterCode() {

        when(jdbcTemplate.queryForList(anyString(), anyString()))
                .thenReturn(setupMatterCodeQueryDataset());

        List<MatterCode> dataReturned = classUnderTest.fetchMatterCodeTwos("CODE1");
        assertEquals(2, dataReturned.size());
        assertEquals("mt1", dataReturned.get(0).getMatterCodeId());
        assertEquals("", dataReturned.get(1).getDescription());
    }

    @Test
    void shouldThrowExceptionIfMatterCode2NotReadCorrectly() {

        doThrow(new DataAccessException(""){}).when(jdbcTemplate).queryForList(anyString(), anyString());

        assertThrows(DatabaseReadException.class,
                () -> classUnderTest.fetchMatterCodeTwos("CODE2"));
    }

    @Test
    void shouldThrowExceptionIfMatterCode1NotFound() {

        doThrow(new MatterCodeNotFoundException(""){}).when(jdbcTemplate).queryForList(anyString(), anyString());

        assertThrows(MatterCodeNotFoundException.class,
                () -> classUnderTest.fetchMatterCodeTwos("CODE3"));
    }
}
