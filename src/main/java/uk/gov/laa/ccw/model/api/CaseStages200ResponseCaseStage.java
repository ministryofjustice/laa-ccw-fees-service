package uk.gov.laa.ccw.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for Case Stage 200 response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaseStages200ResponseCaseStage {
    private String caseStage;
    private String description;
}
