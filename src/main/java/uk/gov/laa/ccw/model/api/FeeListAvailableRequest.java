package uk.gov.laa.ccw.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entity to represent fee list request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeListAvailableRequest {
    private String matterCode1;
    private String matterCode2;
    private String locationCode;
    private String caseStage;
}