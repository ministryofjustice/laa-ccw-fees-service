package uk.gov.laa.ccw.mapping;

import uk.gov.laa.ccw.models.MatterCode;
import uk.gov.laa.ccw.models.MatterCodes200ResponseInnerMatterCode;

public class MatterCodesResponseMapping {
    @SuppressWarnings("java:S3599")
    public static MatterCodes200ResponseInnerMatterCode map(MatterCode inputData) {
        return MatterCodes200ResponseInnerMatterCode.builder()
                .matterCode(inputData.getMatterCodeId())
                .description(inputData.getDescription())
                .build();
    }
}
