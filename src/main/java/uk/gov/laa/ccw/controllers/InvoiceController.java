package uk.gov.laa.ccw.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccw.mapper.api.InvoiceResponseMapper;
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

    @GetMapping("/v1/invoices")
    public InvoiceCreation200Response createInvoice(@RequestBody InvoiceCreateRequest request) {

        validator.validateRequest(request);

        log.info("produced invoice");
        return null;
    }
}
