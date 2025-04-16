package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.FeeDetailsEntity;
import uk.gov.laa.ccw.entity.FeesEntity;
import uk.gov.laa.ccw.entity.VatRateEntity;
import uk.gov.laa.ccw.exceptions.FeesException;
import uk.gov.laa.ccw.exceptions.VatRateNotFoundException;
import uk.gov.laa.ccw.mapper.dao.FeeMapper;
import uk.gov.laa.ccw.mapper.dao.VatRateMapper;
import uk.gov.laa.ccw.model.FeeDetails;
import uk.gov.laa.ccw.model.FeeElement;
import uk.gov.laa.ccw.model.Fee;
import uk.gov.laa.ccw.model.VatRate;
import uk.gov.laa.ccw.model.api.FeeCalculateRequestLevelCode;
import uk.gov.laa.ccw.repository.FeeDetailsRepository;
import uk.gov.laa.ccw.repository.FeesRepository;
import uk.gov.laa.ccw.repository.VatRateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FeesServiceTest {
    @Mock
    private FeesRepository feesRepository;

    @Mock
    private FeeDetailsRepository feeDetailsRepository;

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

        List<FeeElement> dataReturned = classUnderTest.calculateFees("LOC1", "CS1", testLevelCodes);

        Optional<FeeElement> total = dataReturned.stream()
                            .filter(f -> f.getFeeType().contentEquals("totals"))
                            .findAny();

        assertTrue(total.isPresent());
        assertEquals("412.00", total.get().getAmount());
        assertEquals("515.00", total.get().getTotal());
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
                .thenReturn(Fee.builder().levelCodeType("A").levelCode("LEV0").amount(32.00).build());
        when(feeMapper.toFee(feesEntity2))
                .thenReturn(Fee.builder().levelCodeType("O").levelCode("LEV1").amount(64.00).build());
        when(feeMapper.toFee(feesEntity3))
                .thenReturn(Fee.builder().levelCodeType("OF").levelCode("LEV2").amount(128.00).build());
        when(feeMapper.toFee(feesEntity4))
                .thenReturn(Fee.builder().levelCodeType("OU").levelCode("LEV3").amount(58.00).build());
    }

    void setUpMockGetVatRate() {
        VatRateEntity vatRateEntity = VatRateEntity.builder().ratePercentage(25.00).build();
        when(vatRateRepository.findAll())
                .thenReturn(List.of(vatRateEntity));
        when(vatRateMapper.toVatRate(vatRateEntity))
                .thenReturn(VatRate.builder().ratePercentage(25.00).build());
    }

    @Test
    void shouldGetFeeDetailsForLocationAndCaseStage() {
        FeeDetailsEntity feesEntity1 = FeeDetailsEntity.builder()
                .levelCodeType("A")
                .levelCode("LEV0")
                .amount(32.00)
                .description("description0")
                .formQuestion("why")
                .build();
        FeeDetailsEntity feesEntity2 = FeeDetailsEntity.builder()
                .levelCodeType("O")
                .levelCode("LEV1")
                .amount(64.00)
                .description("description1")
                .formQuestion("when")
                .build();
        FeeDetailsEntity feesEntity3 = FeeDetailsEntity.builder()
                .levelCodeType("OF")
                .levelCode("LEV2")
                .amount(128.00)
                .description("description2")
                .formQuestion("which")
                .build();
        FeeDetailsEntity feesEntity4 = FeeDetailsEntity.builder()
                .levelCodeType("OU")
                .levelCode("LEV3")
                .formQuestion("what")
                .amount(58.00)
                .description("description3")
                .build();

        List.of(feesEntity1, feesEntity2, feesEntity3, feesEntity4);

        when(feeDetailsRepository.findByProviderLocationAndCaseStage("LOC1", "CS1"))
                .thenReturn(List.of(feesEntity1, feesEntity2, feesEntity3, feesEntity4));

        when(feeMapper.toFeeDetails(feesEntity1))
                .thenReturn(FeeDetails.builder().amount(32.00).build());
        when(feeMapper.toFeeDetails(feesEntity2))
                .thenReturn(FeeDetails.builder().amount(64.00).build());
        when(feeMapper.toFeeDetails(feesEntity3))
                .thenReturn(FeeDetails.builder().amount(128.00).build());
        when(feeMapper.toFeeDetails(feesEntity4))
                .thenReturn(FeeDetails.builder().amount(58.00).formQuestion("what").build());


        List<FeeDetails> dataReturned = classUnderTest.getFeeDetailsForLocationAndCaseStage("LOC1", "CS1");

        assertEquals(32.0, dataReturned.get(0).getAmount());
        assertEquals("what", dataReturned.get(3).getFormQuestion());
    }

}