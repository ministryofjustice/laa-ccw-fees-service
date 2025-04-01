package uk.gov.laa.ccw.dao;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import uk.gov.laa.ccw.Helpers.FeesHelper;
import uk.gov.laa.ccw.models.FeeRecord;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest()
public class FeesDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private FeesDao classUnderTest;

    @Test
    void shouldFetchFeesForLocation() {

        when(jdbcTemplate.queryForList(anyString(), anyString()))
                .thenReturn(FeesHelper.createFeesTestData()
                        .stream()
                        .filter(f -> f.get("PROVIDER_LOCATION").toString().contentEquals("LOC1"))
                        .collect(Collectors.toList()));

        List<FeeRecord> dataReturned = classUnderTest.fetchFeesForLocation("LOC1");
        assertEquals(4, dataReturned.size());
    }
}
