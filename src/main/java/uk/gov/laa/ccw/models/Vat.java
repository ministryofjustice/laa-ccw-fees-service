package uk.gov.laa.ccw.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for Vat.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vat {
    private Double ratePercentage;
}
