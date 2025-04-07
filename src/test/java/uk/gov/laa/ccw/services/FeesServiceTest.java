package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.dao.FeesDao;
import uk.gov.laa.ccw.dao.VatRatesDao;
import uk.gov.laa.ccw.model.Fee;
import uk.gov.laa.ccw.model.FixedFee;
import uk.gov.laa.ccw.exceptions.FeesException;
import uk.gov.laa.ccw.exceptions.VatRateNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FeesServiceTest {
    @Mock
    private FeesDao feesDao;

    @Mock
    private VatRatesDao vatRatesDao;

    @InjectMocks
    private FeesService classUnderTest;

    private List<FixedFee> testData() {
        return List.of(
                FixedFee.builder()
                        .levelCodeType("A")
                        .levelCode("LEV1")
                        .amount(32.00)
                        .build(),
                FixedFee.builder()
                        .levelCodeType("O")
                        .levelCode("LEV1")
                        .amount(64.00)
                        .build(),
                FixedFee.builder()
                        .levelCodeType("OM")
                        .levelCode("LEV1")
                        .amount(128.00)
                        .build()
        );
    }

    @Test
    void shouldFetchTotalForSameLocationAndCaseStage() {

        when(feesDao.fetchFeesForLocationAndCaseStage(anyString(), anyString()))
                .thenReturn(testData());

        when(vatRatesDao.fetchVat())
                .thenReturn(25.00);

        Fee dataReturned = classUnderTest.calculateFees("LOC1","CS1", new ArrayList<>());

        assertEquals(32, dataReturned.getAmount());
        assertEquals(40, dataReturned.getTotal());
    }

    @Test
    void shouldThrowExceptionWhenFeesDaoThrowsException() {

        doThrow(new FeesException(""){}).when(feesDao)
                .fetchFeesForLocationAndCaseStage(anyString(), anyString());

        assertThrows(FeesException.class,
                () -> classUnderTest.calculateFees("LOC1","CS1", new ArrayList<>()));
    }

    @Test
    void shouldThrowExceptionWhenVatDaoThrowException() {

        doThrow(new VatRateNotFoundException(""){}).when(vatRatesDao).fetchVat();

        assertThrows(VatRateNotFoundException.class,
                () -> classUnderTest.calculateFees("LOC1","CS1", new ArrayList<>()));
    }
}