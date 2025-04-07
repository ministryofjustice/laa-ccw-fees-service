package uk.gov.laa.ccw.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for Case Stage request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaseStageRequest {
    private String matterCode1;
    private String matterCode2;
}