package uk.gov.laa.ccw.dao;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
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
    private JdbcTemplate ccwJdbc;

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

        when(ccwJdbc.queryForList(anyString()))
                .thenReturn(setupMatterCodeQueryDataset());

        List<MatterCode> dataReturned = classUnderTest.fetchAllMatterCodes();
        assertEquals(2, dataReturned.size());
        assertEquals("mt1", dataReturned.get(0).getMatterCodeId());
        assertEquals("", dataReturned.get(1).getDescription());
    }

    @Test
    void shouldThrowExceptionIfMatterCodeNotReadCorrectly() {

        doThrow(new DataAccessException(""){}).when(ccwJdbc).queryForList(anyString());

        assertThrows(DatabaseReadException.class,
                () -> classUnderTest.fetchAllMatterCodes());
    }

}
