package uk.gov.laa.ccw.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccw.mapper.api.InvoiceResponseMapper;
import uk.gov.laa.ccw.model.Invoice;
import uk.gov.laa.ccw.model.api.InvoiceCreateRequest;
import uk.gov.laa.ccw.model.api.InvoiceCreation200Response;
import uk.gov.laa.ccw.services.InvoiceService;
import uk.gov.laa.ccw.services.validators.InvoiceCreationValidator;

@Slf4j
@RequiredArgsConstructor
@RestController
public class InvoiceController {

    private final InvoiceService service;
    private final InvoiceCreationValidator validator;
    private final InvoiceResponseMapper invoiceResponseMapper;

    @PutMapping("/v1/invoices/create")
    public InvoiceCreation200Response createInvoice(@RequestBody InvoiceCreateRequest request) {

        validator.validateRequest(request);

        log.info("Creating invoice");
        Invoice result = service.CreateInvoice(
                request.getProvider(),
                request.getOffice(),
                request.getAmount()
            );

        log.info("produced invoice ID {} invoice number {}", result.getInvoiceId(), result.getInvoiceNumber());
        return invoiceResponseMapper.toInvoiceCreationResponse(result);
    }
}
