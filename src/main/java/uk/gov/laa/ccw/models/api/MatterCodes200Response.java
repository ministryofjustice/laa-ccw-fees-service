package uk.gov.laa.ccw.models.api;

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
    public List<MatterCodes200ResponseMatterCode> matterCodes;
}