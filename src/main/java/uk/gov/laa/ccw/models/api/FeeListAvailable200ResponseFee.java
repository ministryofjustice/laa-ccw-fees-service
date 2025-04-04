package uk.gov.laa.ccw.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeListAvailable200ResponseFee {
    private String amount;
    private String levelCode;
    private String type;
    private String description;
}
