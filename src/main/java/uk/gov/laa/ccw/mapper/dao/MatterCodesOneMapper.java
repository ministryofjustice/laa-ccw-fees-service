package uk.gov.laa.ccw.mapper.dao;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccw.entity.MatterCodesOneEntity;
import uk.gov.laa.ccw.models.MatterCode;

/**
 * Mapper class for matter codes one.
 */
@Mapper(componentModel = "spring")
public interface MatterCodesOneMapper {

    /**
     * Maps the given matter codes one entity to a matter code.
     *
     * @param entity the matter codes one entity
     * @return the matter code
     */
    @Mapping(target = "description", defaultValue = "")
    MatterCode toMatterCode(MatterCodesOneEntity entity);
}
