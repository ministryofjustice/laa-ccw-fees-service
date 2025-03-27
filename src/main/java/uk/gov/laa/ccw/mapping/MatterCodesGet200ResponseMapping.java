package uk.gov.laa.ccw.mapping;

import uk.gov.laa.ccw.models.MatterCode;

import java.util.Map;

public class MatterCodesGet200ResponseMapping {
    @SuppressWarnings("java:S3599")
    public static String map(MatterCode inputData) {
        return inputData.getMatterCodeId();
    }
}
