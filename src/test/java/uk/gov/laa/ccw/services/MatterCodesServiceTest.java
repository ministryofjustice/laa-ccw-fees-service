package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.MatterCodesOneEntity;
import uk.gov.laa.ccw.entity.MatterCodeCombinationsEntity;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.mapper.dao.MatterCodeMapper;
import uk.gov.laa.ccw.model.MatterCode;
import uk.gov.laa.ccw.repository.MatterCodeCombinationsRepository;
import uk.gov.laa.ccw.repository.MatterCodesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MatterCodesServiceTest {

    @Mock
    private MatterCodesRepository matterCodesRepository;

    @Mock
    private MatterCodeCombinationsRepository matterCodeCombinationsRepository;

    @Mock
    private MatterCodeMapper matterCodeMapper;

    @InjectMocks
    private MatterCodesService classUnderTest;

    @Test
    void shouldFetchAllMatterCodes() {
        MatterCodesOneEntity mt1AEntity = MatterCodesOneEntity.builder().matterCodeId("mt1A").build();
        MatterCodesOneEntity mt1BEntity = MatterCodesOneEntity.builder().matterCodeId("mt1B").build();
        when(matterCodesRepository.findByLawType("FAM")).thenReturn(List.of(mt1AEntity, mt1BEntity));
        when(matterCodeMapper.toMatterCode(mt1AEntity)).thenReturn(MatterCode.builder().matterCodeId("mt1A").build());
        when(matterCodeMapper.toMatterCode(mt1BEntity)).thenReturn(MatterCode.builder().matterCodeId("mt1B").build());

        List<MatterCode> dataReturned = classUnderTest.getAllMatterCodes("FAM");
        assertEquals(2, dataReturned.size());
        assertEquals("mt1A", dataReturned.get(0).getMatterCodeId());
        assertEquals("mt1B", dataReturned.get(1).getMatterCodeId());
    }

    @Test
    void shouldFetchMatterCodesForSpecificMatterCodeOne() {

        String matterCodeOne = "mt1A";
        MatterCodeCombinationsEntity mt2AEntity = MatterCodeCombinationsEntity.builder().matterCodeId("mt2A").build();
        MatterCodeCombinationsEntity mt2BEntity = MatterCodeCombinationsEntity.builder().matterCodeId("mt2B").build();

        when(matterCodesRepository.findById(matterCodeOne))
                .thenReturn(Optional.of(MatterCodesOneEntity.builder().matterCodeId(matterCodeOne).build()));
        when(matterCodeCombinationsRepository.findAllByMatterCode1(matterCodeOne))
                .thenReturn(List.of(mt2AEntity, mt2BEntity));
        when(matterCodeMapper.toMatterCode(mt2AEntity)).thenReturn(MatterCode.builder().matterCodeId("mt2A").build());
        when(matterCodeMapper.toMatterCode(mt2BEntity)).thenReturn(MatterCode.builder().matterCodeId("mt2B").build());

        List<MatterCode> dataReturned = classUnderTest.getAllMatterTwosForMatterCodeOne(matterCodeOne);
        assertEquals(2, dataReturned.size());
        assertEquals("mt2A", dataReturned.get(0).getMatterCodeId());
        assertEquals("mt2B", dataReturned.get(1).getMatterCodeId());
    }


    @Test
    void shouldThrowExceptionIfNoMatterCodes() {
        when(matterCodesRepository.findByLawType("FAM"))
                .thenReturn(new ArrayList<>());

        assertThrows(MatterCodeNotFoundException.class,
                () -> classUnderTest.getAllMatterCodes("FAM"));
    }

    @Test
    void shouldThrowExceptionIfNoMatterCodesForMatterCodeTwo() {
        when(matterCodesRepository.findById("mt1"))
                .thenReturn(Optional.empty());

        assertThrows(MatterCodeNotFoundException.class,
                () -> classUnderTest.getAllMatterTwosForMatterCodeOne("mt1"));
    }

    @Test
    void shouldThrowExceptionIfNoMatterCodesTwo() {
        when(matterCodesRepository.findById("mt1"))
                .thenReturn(Optional.of(MatterCodesOneEntity.builder().matterCodeId("mt1").build()));
        when(matterCodeCombinationsRepository.findAllByMatterCode1("mt1"))
                .thenReturn(new ArrayList<>());

        assertThrows(MatterCodeNotFoundException.class,
                () -> classUnderTest.getAllMatterTwosForMatterCodeOne("mt1"));
    }
}
