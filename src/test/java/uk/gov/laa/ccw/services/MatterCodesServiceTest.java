package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import uk.gov.laa.ccw.dao.MatterCodesDao;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.models.MatterCode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
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

        when(matterCodesDao.fetchAllMatterCodes("FAM"))
                .thenReturn(setupMatterCodeDataset());

        List<MatterCode> dataReturned = classUnderTest.getAllMatterCodes("FAM");
        assertEquals(2, dataReturned.size());
        assertEquals("mt1", dataReturned.get(0).getMatterCodeId());
        assertEquals("mt2", dataReturned.get(1).getMatterCodeId());
    }

    @Test
    void shouldFetchMatterCodesForSpecificMatterCodeOne() {

        when(matterCodesDao.fetchMatterCodeTwos(anyString()))
                .thenReturn(setupMatterCodeDataset());

        List<MatterCode> dataReturned = classUnderTest.getAllMatterTwosForMatterCodeOne("CODE1");
        assertEquals(2, dataReturned.size());
        assertEquals("mt1", dataReturned.get(0).getMatterCodeId());
        assertEquals("mt2", dataReturned.get(1).getMatterCodeId());
    }

    @Test
    void shouldThrowExceptionWhenDaoThrowsDatabaseReadException() {

        doThrow(new DatabaseReadException(""){}).when(matterCodesDao).fetchMatterCodeTwos(anyString());

        assertThrows(DatabaseReadException.class,
                () -> classUnderTest.getAllMatterTwosForMatterCodeOne("CODE1"));
    }

    @Test
    void shouldThrowExceptionWhenDaoThrowsMatterCodeNotFoundException() {
        doThrow(new MatterCodeNotFoundException(""){}).when(matterCodesDao).fetchMatterCodeTwos(anyString());

        assertThrows(MatterCodeNotFoundException.class,
                () -> classUnderTest.getAllMatterTwosForMatterCodeOne("CODE1"));
    }

}
