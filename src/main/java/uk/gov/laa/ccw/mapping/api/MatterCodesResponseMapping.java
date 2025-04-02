package uk.gov.laa.ccw.mapping.api;

import uk.gov.laa.ccw.models.MatterCode;
import uk.gov.laa.ccw.models.api.MatterCodes200ResponseMatterCode;

/**
 * Mapping class between MatterCode and MatterCodes200ResponseMatterCode.
 */
public class MatterCodesResponseMapping {

    /**
     * Maps the given MatterCode to a MatterCodes200ResponseMatterCode.
     *
     * @param matterCode the matter code
     * @return the MatterCodes200ResponseMatterCode
     */
    public static MatterCodes200ResponseMatterCode map(MatterCode matterCode) {
        return MatterCodes200ResponseMatterCode.builder()
                .matterCode(matterCode.getMatterCodeId())
                .description(matterCode.getDescription())
                .build();
    }

}
