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
public class VatRatesDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private VatRatesDao classUnderTest;

    @Test
    void shouldFetchVat() {
        List<Map<String, Object>> vatDataSet = new ArrayList<Map<String, Object>>();
        Map<String, Object> rowSet = new HashMap<String, Object>();
        rowSet.put("RATE_PERCENTAGE", "12.34");
        vatDataSet.add(rowSet);

        when(jdbcTemplate.queryForList(anyString()))
                .thenReturn(vatDataSet
                        .stream()
                        .toList());

        Double dataReturned = classUnderTest.fetchVat();
        assertEquals(12.34, dataReturned);
    }

    @Test
    void shouldThrowExceptionIfVatNotReadCorrectly() {

        doThrow(new DataAccessException(""){}).when(jdbcTemplate).queryForList(anyString());

        assertThrows(DatabaseReadException.class,
                () -> classUnderTest.fetchVat());
    }

}
