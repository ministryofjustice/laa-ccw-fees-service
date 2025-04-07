package uk.gov.laa.ccw.mapper.dao;

import org.mapstruct.Mapper;
import uk.gov.laa.ccw.model.FixedFee;
import uk.gov.laa.ccw.entity.FeeEntity;

/**
 * mapper class for fee dao.
 */
@Mapper(componentModel = "spring")
public interface FeeMapper {
    /**
     * Cpnverts fee entity into a fee object.
     */
    FixedFee toFee(FeeEntity feeEntity);
}