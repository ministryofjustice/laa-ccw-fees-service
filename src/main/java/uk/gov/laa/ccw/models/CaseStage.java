package uk.gov.laa.ccw.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for Case Stage.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaseStage {
    private String caseStageId;
    private String description;
}
