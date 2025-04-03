package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.laa.ccw.dao.FeesDao;
import uk.gov.laa.ccw.dao.VatRatesDao;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.FeeRecord;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@SpringBootTest()
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
                        .levelCodeType("A")
                        .levelCode("LEV1")
                        .amount(32.00)
                        .build(),
                FeeRecord.builder()
                        .levelCodeType("O")
                        .levelCode("LEV1")
                        .amount(64.00)
                        .build(),
                FeeRecord.builder()
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
    void shouldThrowExceptionWhenFeesDaoThrowsDatabaseReadException() {

        doThrow(new DatabaseReadException(""){}).when(feesDao)
                .fetchFeesForLocationAndCaseStage(anyString(), anyString());

        assertThrows(DatabaseReadException.class,
                () -> classUnderTest.calculateFees("LOC1","CS1", new ArrayList<>()));
    }

    @Test
    void shouldThrowExceptionWhenVatDaoThrowsDatabaseReadException() {

        doThrow(new DatabaseReadException(""){}).when(vatRatesDao).fetchVat();

        assertThrows(DatabaseReadException.class,
                () -> classUnderTest.calculateFees("LOC1","CS1", new ArrayList<>()));
    }
}
