package uk.gov.laa.ccw.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity(name = "FeeschemeScenarioAssociationEntity")
@Table(name = "feescheme_scenario_association", schema = "ccw")
public class FeeschemeScenarioAssociationEntity {
    @Column(name = "fee_type", nullable = false, length = Integer.MAX_VALUE)
    private String feeType;

    @Column(name = "applicable_for_defendant_uplift", nullable = false)
    private Boolean applicableForDefendantUplift = false;

    @Column(name = "applicable_for_case_uplift", nullable = false)
    private Boolean applicableForCaseUplift = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "feescheme_id", nullable = false)
    private FeeschemeEntity feescheme;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "scenario_id", nullable = false)
    private ScenarioEntity scenario;
    @Id
    private Long id;

}