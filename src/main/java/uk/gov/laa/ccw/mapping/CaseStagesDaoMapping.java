package uk.gov.laa.ccw.mapping;

import uk.gov.laa.ccw.models.CaseStage;

import java.util.Map;

/**
 * Mapping class between query data and CaseStage.
 */
public class CaseStagesDaoMapping {

    /**
     * Maps the given query data to a CaseStage.
     *
     * @param queryData the query data
     * @return the CaseStage
     */
    public static CaseStage mapAllCaseStages(Map<String, Object> queryData) {
        CaseStage.CaseStageBuilder builder = CaseStage.builder();
        if (queryData.get("CASE_STAGE_ID") != null) {
            builder.caseStageId(queryData.get("CASE_STAGE_ID").toString());
        }

        if (queryData.get("DESCRIPTION") != null) {
            builder.description(queryData.get("DESCRIPTION").toString());
        }

        return builder.build();
    }
}
