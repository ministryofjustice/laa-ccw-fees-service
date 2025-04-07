package uk.gov.laa.ccw.mapper.dao;

import org.mapstruct.Mapper;
import uk.gov.laa.ccw.entity.VatEntity;

/**
 * Mapper class for vat.
 */
@Mapper(componentModel = "spring")
public interface VatMapper {

    /**
     * Maps the given vat entity to vat.
     *
     * @param entity the vat entity
     * @return the vat
     */
    default Double toVat(VatEntity entity) {
        return entity != null && entity.getRatePercentage() != null ? entity.getRatePercentage() : 0.0;
    }
}
