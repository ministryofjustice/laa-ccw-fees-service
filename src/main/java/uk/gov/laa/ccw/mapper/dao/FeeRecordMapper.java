package uk.gov.laa.ccw.mapper.dao;

import org.mapstruct.Mapper;
import uk.gov.laa.ccw.entity.FeesEntity;
import uk.gov.laa.ccw.models.FeeRecord;

/**
 * Mapper class for fee record.
 */
@Mapper(componentModel = "spring")
public interface FeeRecordMapper {

    /**
     * Maps the given fees entity to fees record.
     *
     * @param entity the fees entity
     * @return the fees
     */
    FeeRecord toFeeRecord(FeesEntity entity);
}
