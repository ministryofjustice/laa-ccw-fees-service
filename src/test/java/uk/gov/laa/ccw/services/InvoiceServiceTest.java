package uk.gov.laa.ccw.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.InvoiceEntity;
import uk.gov.laa.ccw.exceptions.InvoiceException;
import uk.gov.laa.ccw.mapper.dao.InvoiceMapper;
import uk.gov.laa.ccw.model.Invoice;
import uk.gov.laa.ccw.repository.InvoiceRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private InvoiceMapper invoiceMapper;

    @InjectMocks
    private InvoiceService classUnderTest;

    @Test
    void shouldReturnValidInvoice() {

        when(invoiceRepository.fetchLastInvoiceNumber(anyString(), anyString()))
                .thenReturn(Optional.of(23));

        when(invoiceRepository.save(any(InvoiceEntity.class)))
                .thenReturn(InvoiceEntity.builder().build());

        when (invoiceMapper.toInvoice(any(InvoiceEntity.class)))
                .thenReturn(
                        Invoice.builder()
                                .invoiceId("")
                                .feeAmount(100.0)
                                .providerId("")
                                .invoiceDate(LocalDateTime.now())
                                .invoiceNumber(24)
                                .build()
                        );

        var result = classUnderTest.createInvoice("PROV1","OFF1", 0.00);

        assertEquals(24, result.getInvoiceNumber());
    }

    @Test
    void shouldThrowExceptionWhenInvoiceNotFound() {

        when(invoiceRepository.fetchLastInvoiceNumber(anyString(), anyString()))
                .thenReturn(Optional.of(23));

        doThrow(new InvoiceException(""){}).when(invoiceRepository).save(any(InvoiceEntity.class));

        assertThrows(InvoiceException.class, () -> classUnderTest.createInvoice("PROV1","OFF1", 0.00));
    }

}
