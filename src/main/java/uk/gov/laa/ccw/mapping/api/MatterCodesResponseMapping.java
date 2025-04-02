package uk.gov.laa.ccw.mapping.api;

import uk.gov.laa.ccw.models.MatterCode;
import uk.gov.laa.ccw.models.api.MatterCodes200ResponseMatterCode;

public class MatterCodesResponseMapping {
    public static MatterCodes200ResponseMatterCode map(MatterCode inputData) {
        return MatterCodes200ResponseMatterCode.builder()
                .matterCode(inputData.getMatterCodeId())
                .description(inputData.getDescription())
                .build();
    }

}
