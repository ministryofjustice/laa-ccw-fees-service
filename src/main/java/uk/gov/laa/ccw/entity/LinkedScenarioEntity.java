package uk.gov.laa.ccw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@jakarta.persistence.Entity(name = "LinkedScenarioEntity")
@Table(name = "linked_scenarios", schema = "ccw")
public class LinkedScenarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fee_scheme_id", nullable = false)
    private FeeschemeEntity feeScheme;

    @Column(name = "modifier", nullable = false, precision = 4, scale = 3)
    private BigDecimal modifier;

}