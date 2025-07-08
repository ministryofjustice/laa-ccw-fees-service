package uk.gov.laa.ccw.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity(name = "FeeschemeFixedFeeAssociationEntity")
@Table(name = "feescheme_fixed_fee_association", schema = "ccw")
public class FeeschemeFixedFeeAssociationEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fixed_fee_id", nullable = false)
    private FeeDetailsEntity fixedFee;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fee_scheme_id", nullable = false)
    private FeeschemeEntity feeScheme;

    //TODO [Reverse Engineering] generate columns from DB
}