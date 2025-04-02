package uk.gov.laa.ccw.mapping.api;

import uk.gov.laa.ccw.models.CaseStage;
import uk.gov.laa.ccw.models.CaseStages200ResponseCaseStage;

public class CaseStagesResponseMapping {
    public static CaseStages200ResponseCaseStage map(CaseStage inputData) {
        return CaseStages200ResponseCaseStage.builder()
                .caseStage(inputData.getCaseStageId())
                .description(inputData.getDescription())
                .build();
    }
}
