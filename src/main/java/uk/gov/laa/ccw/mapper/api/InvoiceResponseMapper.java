package uk.gov.laa.ccw.mapper.api;

import org.mapstruct.Mapper;
import uk.gov.laa.ccw.model.Invoice;
import uk.gov.laa.ccw.model.api.InvoiceCreation200Response;

/**
 * Mapping class from Invoices to the api respnse.
 */
@Mapper(componentModel = "spring")
public interface InvoiceResponseMapper {

    /**
     * Mapping method from Invoices to the api respnse.
     */
    InvoiceCreation200Response toInvoiceCreationResponse(Invoice invoice);
}