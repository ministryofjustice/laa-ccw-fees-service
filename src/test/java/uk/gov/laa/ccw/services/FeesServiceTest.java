package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.FeesEntity;
import uk.gov.laa.ccw.entity.VatRateEntity;
import uk.gov.laa.ccw.exceptions.FeesException;
import uk.gov.laa.ccw.exceptions.VatRateNotFoundException;
import uk.gov.laa.ccw.mapper.dao.FeeMapper;
import uk.gov.laa.ccw.mapper.dao.VatRateMapper;
import uk.gov.laa.ccw.model.FeeTotals;
import uk.gov.laa.ccw.model.FixedFee;
import uk.gov.laa.ccw.model.VatRate;
import uk.gov.laa.ccw.model.api.FeeCalculateRequestLevelCode;
import uk.gov.laa.ccw.repository.FeesRepository;
import uk.gov.laa.ccw.repository.VatRateRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FeesServiceTest {
    @Mock
    private FeesRepository feesRepository;

    @Mock
    private FeeMapper feeMapper;

    @Mock
    private VatRateRepository vatRateRepository;

    @Mock
    private VatRateMapper vatRateMapper;

    @InjectMocks
    private FeesService classUnderTest;

    @Test
    void shouldFetchTotalForSameLocationAndCaseStage() {
        List<FeeCalculateRequestLevelCode> testLevelCodes = List.of(
                FeeCalculateRequestLevelCode.builder()
                        .levelCode("LEV1")
                        .build(),
                FeeCalculateRequestLevelCode.builder()
                        .levelCode("LEV2")
                        .fee(200.0)
                        .build(),
                FeeCalculateRequestLevelCode.builder()
                        .levelCode("LEV3")
                        .units(2.0)
                        .build()
        );

        setUpMockGetFeesForLocationAndCaseStage();
        setUpMockGetVatRate();

        FeeTotals dataReturned = classUnderTest.calculateFees("LOC1", "CS1", testLevelCodes);

        assertEquals(412, dataReturned.getAmount());
        assertEquals(515, dataReturned.getTotal());
    }

    @Test
    void shouldThrowExceptionWhenFeesDaoThrowsException() {

        when(feesRepository.findByProviderLocationAndCaseStage("LOC1", "CS1"))
                .thenReturn(new ArrayList<>());

        assertThrows(FeesException.class,
                () -> classUnderTest.calculateFees("LOC1", "CS1", new ArrayList<>()));
    }

    @Test
    void shouldThrowExceptionWhenVatDaoThrowException() {

        setUpMockGetFeesForLocationAndCaseStage();

       when(vatRateRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(VatRateNotFoundException.class,
                () -> classUnderTest.calculateFees("LOC1", "CS1", new ArrayList<>()));
    }

    void setUpMockGetFeesForLocationAndCaseStage() {
        FeesEntity feesEntity1 = FeesEntity.builder()
                .levelCodeType("A")
                .levelCode("LEV0")
                .amount(32.00)
                .build();
        FeesEntity feesEntity2 = FeesEntity.builder()
                .levelCodeType("O")
                .levelCode("LEV1")
                .amount(64.00)
                .build();
        FeesEntity feesEntity3 = FeesEntity.builder()
                .levelCodeType("OF")
                .levelCode("LEV2")
                .amount(128.00)
                .build();
        FeesEntity feesEntity4 = FeesEntity.builder()
                .levelCodeType("OU")
                .levelCode("LEV3")
                .amount(58.00)
                .build();

        List.of(feesEntity1, feesEntity2, feesEntity3, feesEntity4);

        when(feesRepository.findByProviderLocationAndCaseStage("LOC1", "CS1"))
                .thenReturn(List.of(feesEntity1, feesEntity2, feesEntity3, feesEntity4));

        when(feeMapper.toFee(feesEntity1))
                .thenReturn(FixedFee.builder().levelCodeType("A").levelCode("LEV0").amount(32.00).build());
        when(feeMapper.toFee(feesEntity2))
                .thenReturn(FixedFee.builder().levelCodeType("O").levelCode("LEV1").amount(64.00).build());
        when(feeMapper.toFee(feesEntity3))
                .thenReturn(FixedFee.builder().levelCodeType("OF").levelCode("LEV2").amount(128.00).build());
        when(feeMapper.toFee(feesEntity4))
                .thenReturn(FixedFee.builder().levelCodeType("OU").levelCode("LEV3").amount(58.00).build());
    }

    void setUpMockGetVatRate() {
        VatRateEntity vatRateEntity = VatRateEntity.builder().ratePercentage(25.00).build();
        when(vatRateRepository.findAll())
                .thenReturn(List.of(vatRateEntity));
        when(vatRateMapper.toVatRate(vatRateEntity))
                .thenReturn(VatRate.builder().ratePercentage(25.00).build());
    }

}