package uk.gov.laa.ccw.mapping.api;

import uk.gov.laa.ccw.models.CaseStage;
import uk.gov.laa.ccw.models.api.CaseStages200ResponseCaseStage;

/**
 * Mapping class between CaseStage and CaseStages200ResponseCaseStage.
 */
public class CaseStagesResponseMapping {

    /**
     * Maps the given CaseStage to a CaseStages200ResponseCaseStage.
     *
     * @param caseStage the case stage
     * @return the CaseStages200ResponseCaseStage
     */
    public static CaseStages200ResponseCaseStage map(CaseStage caseStage) {
        return CaseStages200ResponseCaseStage.builder()
                .caseStage(caseStage.getCaseStageId())
                .description(caseStage.getDescription())
                .build();
    }
}
