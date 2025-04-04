package uk.gov.laa.ccw.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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