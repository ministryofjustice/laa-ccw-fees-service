package uk.gov.laa.ccw.mapper.dao;

import org.mapstruct.Mapper;
import uk.gov.laa.ccw.entity.CaseStagesEntity;
import uk.gov.laa.ccw.model.CaseStage;

/**
 * Mapper class for case stages.
 */
@Mapper(componentModel = "spring")
public interface CaseStagesMapper {

    /**
     * Maps the given case stages entity to case stage.
     *
     * @param entity the case stages entity
     * @return the case stages
     */
    CaseStage toCaseStage(CaseStagesEntity entity);
}
