package uk.gov.laa.ccw.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for Fee Record.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeRecord {
    private String providerLocation;
    private String levelCode;
    private String caseStage;
    private Double amount;
}
