package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import uk.gov.laa.ccw.dao.MatterCodesDao;
import uk.gov.laa.ccw.models.MatterCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatterCodesServiceTest {
    @Mock
    private MatterCodesDao matterCodesDao;

    @InjectMocks
    private MatterCodesService classUnderTest;

    private List<MatterCode> setupMatterCodeDataset() {
        List<MatterCode> dataSet = new ArrayList<MatterCode>();
        MatterCode rowSet = MatterCode.builder()
                            .matterCodeId("mt1")
                            .build();
        dataSet.add(rowSet);
        rowSet = MatterCode.builder()
                .matterCodeId("mt2")
                .build();
        dataSet.add(rowSet);
        return dataSet;
    }

    @Test
    void shouldFetchAllMatterCodes() {

        when(matterCodesDao.fetchAllMatterCodes())
                .thenReturn(setupMatterCodeDataset());

        List<String> dataReturned = classUnderTest.getAllMatterCodes();
        assertEquals(2, dataReturned.size());
        assertEquals("mt1", dataReturned.get(0));
        assertEquals("mt2", dataReturned.get(1));
    }
}
