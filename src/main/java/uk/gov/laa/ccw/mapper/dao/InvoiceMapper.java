package uk.gov.laa.ccw.mapper.dao;

import org.mapstruct.Mapper;
import uk.gov.laa.ccw.entity.InvoiceEntity;
import uk.gov.laa.ccw.model.Invoice;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice toInvoice(InvoiceEntity invoiceEntity);
}
