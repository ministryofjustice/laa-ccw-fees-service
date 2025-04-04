package uk.gov.laa.ccw.entity.id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Id class for Fees entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeesId {
    private String caseStage;
    private String levelCode;
    private String providerLocation;
}
