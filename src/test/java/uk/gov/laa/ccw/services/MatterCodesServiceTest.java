package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.dao.MatterCodeDao;
import uk.gov.laa.ccw.entity.MatterCodesOneEntity;
import uk.gov.laa.ccw.entity.MatterCodesTwoEntity;
import uk.gov.laa.ccw.mapper.dao.MatterCodeMapper;
import uk.gov.laa.ccw.model.MatterCode;
import uk.gov.laa.ccw.repository.MatterCodesRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MatterCodesServiceTest {

    @Mock
    private MatterCodeDao matterCodesDao;

    @Mock
    private MatterCodeMapper matterCodeMapper;

    @InjectMocks
    private MatterCodesService classUnderTest;

    @Test
    void shouldFetchAllMatterCodes() {
        MatterCode mt1AEntity = MatterCode.builder().matterCodeId("mt1A").build();
        MatterCode mt1BEntity = MatterCode.builder().matterCodeId("mt1B").build();
        when(matterCodesDao.findMatterCodesByLawType("FAM")).thenReturn(List.of(mt1AEntity, mt1BEntity));

        List<MatterCode> dataReturned = classUnderTest.getAllMatterCodes("FAM");
        assertEquals(2, dataReturned.size());
        assertEquals("mt1A", dataReturned.get(0).getMatterCodeId());
        assertEquals("mt1B", dataReturned.get(1).getMatterCodeId());
    }

    @Test
    void shouldFetchMatterCodesForSpecificMatterCodeOne() {

        String matterCodeOne = "mt1A";
        MatterCode mt2AEntity = MatterCode.builder().matterCodeId("mt2A").build();
        MatterCode mt2BEntity = MatterCode.builder().matterCodeId("mt2B").build();
        when(matterCodesDao.findMatterCodeTwosByMatterCodeOne(matterCodeOne))
                .thenReturn(List.of(mt2AEntity, mt2BEntity));

        List<MatterCode> dataReturned = classUnderTest.getAllMatterTwosForMatterCodeOne(matterCodeOne);
        assertEquals(2, dataReturned.size());
        assertEquals("mt2A", dataReturned.get(0).getMatterCodeId());
        assertEquals("mt2B", dataReturned.get(1).getMatterCodeId());
    }
}
