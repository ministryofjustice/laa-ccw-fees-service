package uk.gov.laa.ccw.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.laa.ccw.entity.id.FeesId;

import java.math.BigDecimal;

/**
 * The entity class for Fixed Fees.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(FeesId.class)
@Table(name = "FIXED_FEES")
public class FeesEntity {
    @Id
    private String caseStage;

    @Id
    private String levelCode;

    @Id
    private String providerLocation;

    private Double amount;
}
