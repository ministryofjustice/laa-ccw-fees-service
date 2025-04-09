package uk.gov.laa.ccw.mapper.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.model.Invoice;
import uk.gov.laa.ccw.model.api.InvoiceCreation200Response;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceResponseMapper {

        public InvoiceCreation200Response toInvoiceCreationResponse(Invoice data) {

        return InvoiceCreation200Response.builder()
                .invoiceId(data.getInvoiceId())
                .invoiceNumber(data.getInvoiceNumber().toString())
                .build();
    }
}
