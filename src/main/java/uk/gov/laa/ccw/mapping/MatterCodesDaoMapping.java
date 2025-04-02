package uk.gov.laa.ccw.mapping;

import uk.gov.laa.ccw.models.MatterCode;

import java.util.Map;

/**
 * Mapping class between query data and MatterCode.
 */
public class MatterCodesDaoMapping {

    /**
     * Maps the given query data to a MatterCode.
     *
     * @param queryData the query data
     * @return the MatterCode
     */
    public static MatterCode mapAllMatterCodes(Map<String, Object> queryData) {

        MatterCode.MatterCodeBuilder builder = MatterCode.builder();

        if (queryData.get("MATTER_CODE_ID") != null) {
            builder.matterCodeId(queryData.get("MATTER_CODE_ID").toString());
        }

        if (queryData.get("DESCRIPTION") != null) {
            builder.description(queryData.get("DESCRIPTION").toString());
        } else {
            builder.description("");
        }

        return builder.build();
    }
}
