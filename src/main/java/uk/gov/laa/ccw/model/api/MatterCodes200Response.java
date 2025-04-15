package uk.gov.laa.ccw.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The model class for Matter Codes 200 response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatterCodes200Response {
    private List<MatterCodes200ResponseMatterCode> matterCodes;
}