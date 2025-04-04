package uk.gov.laa.ccw.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for Fee Calculate Request Level Code.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeCalculateRequestLevelCode {
    private String levelCode;
    private Double fee;
}
