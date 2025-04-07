package uk.gov.laa.ccw.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entity to represent data retrieved as fee.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FIXED_FEES")
public class FeeEntity {
    @Id
    private int feeId;
    private String levelCodeType;
    private String levelCode;
    private Double amount;
    private String description;
}