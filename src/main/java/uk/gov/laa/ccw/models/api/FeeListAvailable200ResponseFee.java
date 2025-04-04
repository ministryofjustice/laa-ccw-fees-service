package uk.gov.laa.ccw.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for Fee List Available 200 response fee.
 */
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
