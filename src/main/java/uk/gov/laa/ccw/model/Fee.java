package uk.gov.laa.ccw.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for Fee.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fee {
    private Double amount;
    private Double vat;
    private Double total;
}
