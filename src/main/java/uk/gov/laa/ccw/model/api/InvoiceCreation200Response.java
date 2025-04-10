package uk.gov.laa.ccw.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entity to represent invoice creation api response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCreation200Response {
    private String invoiceId;
    private String invoiceNumber;
}