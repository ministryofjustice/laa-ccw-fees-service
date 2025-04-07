package uk.gov.laa.ccw.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.VatRateEntity;
import uk.gov.laa.ccw.exceptions.VatRateNotFoundException;
import uk.gov.laa.ccw.mapper.dao.VatRateMapper;
import uk.gov.laa.ccw.model.VatRate;
import uk.gov.laa.ccw.repository.VatRateRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VatRatesDaoTest {
    @Mock
    private VatRateRepository repository;

    @Mock
    private VatRateMapper mapper;

    @InjectMocks
    private VatRatesDao classUnderTest;

    private final double VAT_RATE = 25.00;

    VatRate getMockVatRate() {
        return VatRate.builder()
                .ratePercentage(VAT_RATE)
                .build();
    }

    VatRateEntity getMockVatRateEntity() {
        return VatRateEntity.builder()
                .ratePercentage(VAT_RATE)
                .build();
    }

    @Test
    void shouldFetchVat() {

        when(repository.findAll())
                .thenReturn(List.of(getMockVatRateEntity()));
        when(mapper.toVatRate(getMockVatRateEntity()))
                .thenReturn(getMockVatRate());

        Double dataReturned = classUnderTest.fetchVat();
        assertEquals(VAT_RATE, dataReturned);
    }

    @Test
    void shouldThrowExceptionIfVatNotReadCorrectly() {

        doThrow(new VatRateNotFoundException(""){}).when(repository).findAll();

        assertThrows(VatRateNotFoundException.class,
                () -> classUnderTest.fetchVat());
    }

}