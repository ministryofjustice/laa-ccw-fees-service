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
public class FixedFee {
    private String levelCodeType;
    private String levelCode;
    private Double amount;
}