package uk.gov.laa.ccw.mapper.dao;

import org.mapstruct.Mapper;
import uk.gov.laa.ccw.entity.FeeDetailsEntity;
import uk.gov.laa.ccw.entity.FeesEntity;
import uk.gov.laa.ccw.model.FeeDetails;
import uk.gov.laa.ccw.model.FixedFee;

/**
 * mapper class for fee dao.
 */
@Mapper(componentModel = "spring")
public interface FeeMapper {
    /**
     * Cpnverts fee entity into a fee details object.
     */
    FeeDetails toFeeDetails(FeeDetailsEntity feesEntity);

    /**
     * Cpnverts fee entity into a fee object.
     */
    FixedFee toFee(FeesEntity feesEntity);
}