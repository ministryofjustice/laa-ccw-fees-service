package uk.gov.laa.ccw.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for Matter Code 200 response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatterCodes200ResponseMatterCode {
    private String matterCode;
    private String description;
}
