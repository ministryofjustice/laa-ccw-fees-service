package uk.gov.laa.ccw.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity(name = "FeeschemeHourlyFeeAssociationEntity")
@Table(name = "feescheme_hourly_fee_association", schema = "ccw")
public class FeeschemeHourlyFeeAssociationEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fee_scheme_id", nullable = false)
    private FeeschemeEntity feeScheme;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "hourly_fee_id", nullable = false)
    private HourlyFeeEntity hourlyFee;
    @Id
    private Long id;
}