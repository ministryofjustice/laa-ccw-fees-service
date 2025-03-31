package uk.gov.laa.ccw.mapping;

import uk.gov.laa.ccw.models.MatterCode;

import java.util.Map;

public class MatterCodesDaoMapping {
    public static MatterCode mapAllMatterCodes(Map<String, Object> queryData) {

        return new MatterCode() {{
            if (queryData.get("MATTER_CODE_ID") != null) {
                setMatterCodeId(queryData.get("MATTER_CODE_ID").toString());
            } else {
                setMatterCodeId(null);
            }

            if (queryData.get("DESCRIPTION") != null) {
                setDescription(queryData.get("DESCRIPTION").toString());
            } else {
                setDescription("");
            }
        }};
    }
}
