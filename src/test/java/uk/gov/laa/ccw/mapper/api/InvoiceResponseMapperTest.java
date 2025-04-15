package uk.gov.laa.ccw.mapper.api;

import org.junit.jupiter.api.Test;
import uk.gov.laa.ccw.model.Invoice;
import uk.gov.laa.ccw.model.api.InvoiceCreation200Response;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceResponseMapperTest {

    private InvoiceResponseMapper classUnderTest = new InvoiceResponseMapperImpl();

    @Test
    void shouldMapToResponse() {
        Invoice request = Invoice.builder()
                .officeId("officeId")
                .providerId("providerId")
                .invoiceId("invoiceId")
                .invoiceNumber(12345)
                .feeAmount(12.34)
                .invoiceDate(LocalDateTime.now())
                .build();

        InvoiceCreation200Response result = classUnderTest.toInvoiceCreationResponse(request);

        assertNotNull(result);
        assertEquals(result.getInvoiceId(), request.getInvoiceId());
        assertEquals(result.getInvoiceNumber(), request.getInvoiceNumber().toString());
    }

}
