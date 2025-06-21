package uk.gov.laa.ccw.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for FeeTotals.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeeElement {
    private String amount;
}