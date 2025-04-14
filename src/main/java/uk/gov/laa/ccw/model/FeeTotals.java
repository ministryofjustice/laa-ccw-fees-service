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
@AllArgsConstructor
@NoArgsConstructor
public class FeeTotals {
    private String amount;
    private String vat;
    private String total;
}
