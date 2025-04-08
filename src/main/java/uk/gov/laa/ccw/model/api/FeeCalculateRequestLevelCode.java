package uk.gov.laa.ccw.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entity to represent fee calculation request level code.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeCalculateRequestLevelCode {
    private String levelCode;
    private Double fee;
    private Double units;
}