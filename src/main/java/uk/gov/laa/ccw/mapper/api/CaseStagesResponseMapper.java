package uk.gov.laa.ccw.mapper.api;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccw.models.CaseStage;
import uk.gov.laa.ccw.models.api.CaseStages200ResponseCaseStage;

/**
 * Mapper class between CaseStage and CaseStages200ResponseCaseStage.
 */
@Mapper(componentModel = "spring")
public interface CaseStagesResponseMapper {

    /**
     * Maps the given CaseStage to a CaseStages200ResponseCaseStage.
     *
     * @param caseStage the case stage
     * @return the CaseStages200ResponseCaseStage
     */
    @Mapping(target = "description", defaultValue = "")
    CaseStages200ResponseCaseStage toCaseStages200ResponseCaseStage(CaseStage caseStage);

}
