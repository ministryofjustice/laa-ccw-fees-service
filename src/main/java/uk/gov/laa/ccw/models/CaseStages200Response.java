package uk.gov.laa.ccw.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The model class for Case Stages 200 response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaseStages200Response {
    List<CaseStages200ResponseCaseStage> caseStages;
}
