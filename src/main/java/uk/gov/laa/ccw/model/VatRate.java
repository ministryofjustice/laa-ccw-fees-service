package uk.gov.laa.ccw.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entity to represent an internal vat rate.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VatRate {
    private Double ratePercentage;
}