package uk.gov.laa.ccw.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

/**
 * entity to represent data retrieved as fee.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "FeeDetailsEntity")
@Table(name = "FIXED_FEES")
public class FeeDetailsEntity {
    @Id
    @Column(name = "FEE_ID")
    private int feeId;
    @Column(name = "TYPE")
    private String levelCodeType;
    private String levelCode;
    private Double amount;
    private String description;
    @Column(name = "TEXT")
    private String formQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "unit_id")
    private UnitEntity unit;

    @Column(name = "fixed_value", nullable = false, precision = 7, scale = 2)
    private BigDecimal fixedValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "scenario_id")
    private ScenarioEntity scenario;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "activity_id")
    private ActivityEntity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "offence_band_id")
    private OffenceBandEntity offenceBand;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "provider_category_id")
    private ProviderCategoryEntity providerCategory;

}