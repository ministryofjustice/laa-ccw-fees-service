package uk.gov.laa.ccw.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fee200Response {
    private String matterCode1;
    private String matterCode2;
    private String locationCode;
    private String caseStage;
    private Double amount;
    private Double vat;
    private Double total;
}
