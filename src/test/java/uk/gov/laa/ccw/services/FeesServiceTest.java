package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.FeesEntity;
import uk.gov.laa.ccw.entity.VatEntity;
import uk.gov.laa.ccw.mapper.dao.FeeRecordMapper;
import uk.gov.laa.ccw.mapper.dao.VatMapper;
import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.FeeRecord;
import uk.gov.laa.ccw.repository.FeesRepository;
import uk.gov.laa.ccw.repository.VatRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeesServiceTest {

    @Mock
    private FeesRepository feesRepository;

    @Mock
    private FeeRecordMapper feeRecordMapper;

    @Mock
    private VatRepository vatRepository;

    @Mock
    private VatMapper vatMapper;

    @InjectMocks
    private FeesService classUnderTest;

    @Test
    void shouldFetchTotalForSameLocationAndCaseStage() {

        String location = "LOC1";
        FeesEntity fees1Entity = FeesEntity.builder().providerLocation("LOC1")
                .caseStage("CS1").levelCode("LEV1").amount(100.00).build();
        FeesEntity fees2Entity = FeesEntity.builder().providerLocation("LOC1")
                .caseStage("CS1").levelCode("LEV2").amount(200.00).build();
        FeesEntity fees3Entity = FeesEntity.builder().providerLocation("LOC1")
                .caseStage("CS2").levelCode("LEV1").amount(400.00).build();
        FeesEntity fees4Entity = FeesEntity.builder().providerLocation("LOC1")
                .caseStage("CS2").levelCode("LEV2").amount(800.00).build();
        FeesEntity fees5Entity = FeesEntity.builder().providerLocation("LOC2")
                .caseStage("CS2").levelCode("LEV2").amount(1600.00).build();
        VatEntity vatEntity = VatEntity.builder().ratePercentage(25.00).build();

        when(feesRepository.findAllByProviderLocation(location)).thenReturn(List.of(fees1Entity, fees2Entity, fees3Entity, fees4Entity, fees5Entity));
        when(feeRecordMapper.toFeeRecord(fees1Entity)).thenReturn(FeeRecord.builder().providerLocation("LOC1")
                .caseStage("CS1").levelCode("LEV1").amount(100.00).build());
        when(feeRecordMapper.toFeeRecord(fees2Entity)).thenReturn(FeeRecord.builder().providerLocation("LOC1")
                .caseStage("CS1").levelCode("LEV2").amount(200.00).build());
        when(feeRecordMapper.toFeeRecord(fees3Entity)).thenReturn(FeeRecord.builder().providerLocation("LOC1")
                .caseStage("CS2").levelCode("LEV1").amount(400.00).build());
        when(feeRecordMapper.toFeeRecord(fees4Entity)).thenReturn(FeeRecord.builder().providerLocation("LOC1")
                .caseStage("CS2").levelCode("LEV2").amount(800.00).build());
        when(feeRecordMapper.toFeeRecord(fees5Entity)).thenReturn(FeeRecord.builder().providerLocation("LOC2")
                .caseStage("CS2").levelCode("LEV2").amount(1600.00).build());
        when(vatRepository.findAll()).thenReturn(List.of(vatEntity));
        when(vatMapper.toVat(vatEntity)).thenReturn(25.00);

        Fee dataReturned = classUnderTest.calculateFees("LOC1","CS1");

        assertEquals(300, dataReturned.getAmount());
        assertEquals(375, dataReturned.getTotal());
    }


}
