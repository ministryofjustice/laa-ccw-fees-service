package uk.gov.laa.ccw.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entity to represent an internal fee.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeDetails {
    private String levelCode;
    private String levelCodeType;
    private Double amount;
    private String description;
    private String formQuestion;
}
