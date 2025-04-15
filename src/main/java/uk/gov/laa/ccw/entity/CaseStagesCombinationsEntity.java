package uk.gov.laa.ccw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The entity class for Case Stages.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VW_CASE_STAGE_COMBINATIONS")
public class CaseStagesCombinationsEntity {
    @Id
    @Column(name = "CASE_STAGE_ID")
    private String caseStageId;
    private String description;
    @Column(name = "MATTER_CODE_1")
    private String matterCodeOne;
}
