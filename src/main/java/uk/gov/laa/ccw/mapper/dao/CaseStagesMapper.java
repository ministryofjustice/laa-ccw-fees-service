package uk.gov.laa.ccw.mapper.dao;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccw.entity.CaseStagesEntity;
import uk.gov.laa.ccw.models.CaseStage;

/**
 * Mapper class for case stages.
 */
@Mapper(componentModel = "spring")
public interface CaseStagesMapper {

    /**
     * Maps the given case stages entity to case stages.
     *
     * @param entity the case stages entity
     * @return the case stages
     */
    @Mapping(target = "description", defaultValue = "")
    CaseStage toCaseStage(CaseStagesEntity entity);
}
