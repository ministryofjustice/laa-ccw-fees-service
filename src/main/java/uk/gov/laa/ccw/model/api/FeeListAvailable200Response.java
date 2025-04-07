package uk.gov.laa.ccw.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * entity to represent fees available api responaw.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeListAvailable200Response {
    private String matterCode1;
    private String matterCode2;
    private String locationCode;
    private String caseStage;
    private List<FeeListAvailable200ResponseFee> fees;
}