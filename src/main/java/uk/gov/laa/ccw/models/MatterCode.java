package uk.gov.laa.ccw.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for Matter Code.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatterCode {
    private String matterCodeId;
    private String description;
}