package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.dao.FeesDao;
import uk.gov.laa.ccw.dao.VatRatesDao;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.FeeRecord;

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

    private List<FeeRecord> testData() {
        return List.of(
                FeeRecord.builder()
                        .providerLocation("LOC1")
                        .caseStage("CS1")
                        .levelCode("LEV1")
                        .amount(100.00)
                        .build(),
                FeeRecord.builder()
                        .providerLocation("LOC1")
                        .caseStage("CS1")
                        .levelCode("LEV2")
                        .amount(200.00)
                        .build(),
                FeeRecord.builder()
                        .providerLocation("LOC1")
                        .caseStage("CS2")
                        .levelCode("LEV1")
                        .amount(400.00)
                        .build(),
                FeeRecord.builder()
                        .providerLocation("LOC1")
                        .caseStage("CS2")
                        .levelCode("LEV2")
                        .amount(800.00)
                        .build(),
                FeeRecord.builder()
                        .providerLocation("LOC2")
                        .caseStage("CS2")
                        .levelCode("LEV2")
                        .amount(1600.00)
                        .build()
        );
    }

    @Test
    void shouldFetchTotalForSameLocationAndCaseStage() {

        when(feesDao.fetchFeesForLocation(anyString()))
                .thenReturn(testData());

        when(vatRatesDao.fetchVat())
                .thenReturn(25.00);

        Fee dataReturned = classUnderTest.calculateFees("LOC1","CS1");

        assertEquals(300, dataReturned.getAmount());
        assertEquals(375, dataReturned.getTotal());
    }

    @Test
    void shouldThrowExceptionWhenFeesDaoThrowsDatabaseReadException() {

        doThrow(new DatabaseReadException(""){}).when(feesDao).fetchFeesForLocation(anyString());

        assertThrows(DatabaseReadException.class,
                () -> classUnderTest.calculateFees("LOC1","CS1"));
    }

    @Test
    void shouldThrowExceptionWhenVatDaoThrowsDatabaseReadException() {

        doThrow(new DatabaseReadException(""){}).when(vatRatesDao).fetchVat();

        assertThrows(DatabaseReadException.class,
                () -> classUnderTest.calculateFees("LOC1","CS1"));
    }
}
