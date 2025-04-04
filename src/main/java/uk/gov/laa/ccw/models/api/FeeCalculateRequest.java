package uk.gov.laa.ccw.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The model class for Fees Request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeCalculateRequest {
    private String matterCode1;
    private String matterCode2;
    private String locationCode;
    private String caseStage;
    private List<FeeCalculateRequestLevelCode> levelCodes;
}
