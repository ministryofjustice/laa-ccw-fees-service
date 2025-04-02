package uk.gov.laa.ccw.mapping.dao;

import uk.gov.laa.ccw.models.CaseStage;

import java.util.Map;

public class CaseStagesDaoMapping {
    public static CaseStage mapAllCaseStages(Map<String, Object> queryData) {
        return new CaseStage() {{
            if (queryData.get("CASE_STAGE_ID") != null) {
                setCaseStageId(queryData.get("CASE_STAGE_ID").toString());
            } else {
                setCaseStageId(null);
            }

            if (queryData.get("DESCRIPTION") != null) {
                setDescription(queryData.get("DESCRIPTION").toString());
            } else {
                setDescription("");
            }
        }};
    }
}
