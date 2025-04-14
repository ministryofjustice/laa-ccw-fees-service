package uk.gov.laa.ccw.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entity to represent fees calculation api response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeCalculate200Response {
    FeeCalculate200ResponseFee totals;
}