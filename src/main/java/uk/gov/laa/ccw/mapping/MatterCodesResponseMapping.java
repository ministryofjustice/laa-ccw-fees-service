package uk.gov.laa.ccw.mapping;

import uk.gov.laa.ccw.models.MatterCode;
import uk.gov.laa.ccw.models.MatterCodesResponseInnerMatterCode;

public class MatterCodesResponseMapping {
    @SuppressWarnings("java:S3599")
    public static MatterCodesResponseInnerMatterCode map(MatterCode inputData) {
        return MatterCodesResponseInnerMatterCode.builder()
                .matterCode(inputData.getMatterCodeId())
                .description(inputData.getDescription())
                .build();
    }
}
