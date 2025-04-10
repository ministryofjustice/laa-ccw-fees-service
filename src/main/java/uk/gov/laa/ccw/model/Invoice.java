package uk.gov.laa.ccw.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Method to represent an Invoice.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private String providerId;
    private String officeId;
    private Double feeAmount;
    private String invoiceId;
    private Integer invoiceNumber;
    private LocalDateTime invoiceDate;
}
