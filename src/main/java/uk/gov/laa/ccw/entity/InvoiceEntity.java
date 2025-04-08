package uk.gov.laa.ccw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INVOICES")
public class InvoiceEntity {
    @Id
    @Column(name = "INVOICE_ID")
    private String InvoiceId;
    private Double feeAmount;
    private String providerId;
    private String officeId;
    private Integer invoiceNumber;
    private Date invoiceDate;
}
