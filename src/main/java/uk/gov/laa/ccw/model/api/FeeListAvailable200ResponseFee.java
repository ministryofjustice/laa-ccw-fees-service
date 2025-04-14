package uk.gov.laa.ccw.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entity to represent fee list api response fee.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeListAvailable200ResponseFee {
    private String amount;
    private String levelCode;
    private String levelCodeType;
    private String description;
    private String formQuestion;
}