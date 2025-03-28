package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.laa.ccw.dao.MatterCodesDao;
import uk.gov.laa.ccw.models.MatterCode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest()
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

        List<MatterCode> dataReturned = classUnderTest.getAllMatterCodes();
        assertEquals(2, dataReturned.size());
        assertEquals("mt1", dataReturned.get(0).getMatterCodeId());
        assertEquals("mt2", dataReturned.get(1).getMatterCodeId());
    }
}
