package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.laa.ccw.entity.MatterCodesOneEntity;
import uk.gov.laa.ccw.entity.MatterCodesTwoEntity;
import uk.gov.laa.ccw.mapper.dao.MatterCodesOneMapper;
import uk.gov.laa.ccw.mapper.dao.MatterCodesTwoMapper;
import uk.gov.laa.ccw.models.MatterCode;
import uk.gov.laa.ccw.repository.MatterCodesRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MatterCodesServiceTest {

    @Mock
    private MatterCodesRepository matterCodesRepository;

    @Mock
    private MatterCodesOneMapper matterCodesOneMapper;

    @Mock
    private MatterCodesTwoMapper matterCodesTwoMapper;

    @InjectMocks
    private MatterCodesService classUnderTest;

    @Test
    void shouldFetchAllMatterCodes() {
        MatterCodesOneEntity mt1AEntity = MatterCodesOneEntity.builder().matterCodeId("mt1A").build();
        MatterCodesOneEntity mt1BEntity = MatterCodesOneEntity.builder().matterCodeId("mt1B").build();
        when(matterCodesRepository.findAll()).thenReturn(List.of(mt1AEntity, mt1BEntity));
        when(matterCodesOneMapper.toMatterCode(mt1AEntity)).thenReturn(MatterCode.builder().matterCodeId("mt1A").build());
        when(matterCodesOneMapper.toMatterCode(mt1BEntity)).thenReturn(MatterCode.builder().matterCodeId("mt1B").build());


        List<MatterCode> dataReturned = classUnderTest.getAllMatterCodes();
        assertEquals(2, dataReturned.size());
        assertEquals("mt1A", dataReturned.get(0).getMatterCodeId());
        assertEquals("mt1B", dataReturned.get(1).getMatterCodeId());
    }

    @Test
    void shouldFetchMatterCodesForSpecificMatterCodeOne() {

        String matterCodeOne = "CODE1";
        when(matterCodesRepository.findById(matterCodeOne)).thenReturn(Optional.of(MatterCodesOneEntity.builder().matterCodeId("mt1A").build()));
        MatterCodesTwoEntity mt2AEntity = MatterCodesTwoEntity.builder().matterCodeId("mt2A").build();
        MatterCodesTwoEntity mt2BEntity = MatterCodesTwoEntity.builder().matterCodeId("mt2B").build();
        when(matterCodesRepository.findMatterCodesTwosByMatterCodeOne(matterCodeOne))
                .thenReturn(List.of(mt2AEntity, mt2BEntity));
        when(matterCodesTwoMapper.toMatterCode(mt2AEntity)).thenReturn(MatterCode.builder().matterCodeId("mt2A").build());
        when(matterCodesTwoMapper.toMatterCode(mt2BEntity)).thenReturn(MatterCode.builder().matterCodeId("mt2B").build());

        List<MatterCode> dataReturned = classUnderTest.getAllMatterTwosForMatterCodeOne(matterCodeOne);
        assertEquals(2, dataReturned.size());
        assertEquals("mt2A", dataReturned.get(0).getMatterCodeId());
        assertEquals("mt2B", dataReturned.get(1).getMatterCodeId());
    }
}
