package uk.gov.laa.ccw.mapper.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.entity.InvoiceEntity;
import uk.gov.laa.ccw.model.Invoice;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class InvoiceMapperTest {
    @InjectMocks
    private InvoiceMapper mapper = new InvoiceMapperImpl();

    @Test
    void shouldMapEntityToInvoiceModel() {
        InvoiceEntity entity = InvoiceEntity.builder()
                .invoiceDate(LocalDateTime.now())
                .invoiceNumber(23)
                .invoiceId("invoiceId")
                .providerId("PROV1")
                .officeId("OFF1")
                .feeAmount(100.0)
                .build();
        Invoice result = mapper.toInvoice(entity);

        assertNotNull(result);
        assertEquals(result.getInvoiceId(), entity.getInvoiceId());
        assertEquals(result.getInvoiceNumber(), entity.getInvoiceNumber());
        assertEquals(result.getOfficeId(), entity.getOfficeId());
        assertEquals(result.getProviderId(), entity.getProviderId());
        assertEquals(result.getInvoiceDate(), entity.getInvoiceDate());
    }
}
