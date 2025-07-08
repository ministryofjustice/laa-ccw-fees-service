package uk.gov.laa.ccw.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity(name = "FeeschemeGraduatedFeeAssociationEntity")
@Table(name = "feescheme_graduated_fee_association", schema = "ccw")
public class FeeschemeGraduatedFeeAssociationEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fee_scheme_id", nullable = false)
    private FeeschemeEntity feeScheme;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "graduated_fee_id", nullable = false)
    private GraduatedFeeEntity graduatedFee;
    @Id
    private Long id;

}