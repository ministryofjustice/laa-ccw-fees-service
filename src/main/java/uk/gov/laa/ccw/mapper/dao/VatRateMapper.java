package uk.gov.laa.ccw.mapper.dao;

import org.mapstruct.Mapper;
import uk.gov.laa.ccw.entity.VatRateEntity;
import uk.gov.laa.ccw.model.VatRate;

/**
 * The mapper for vat rate.
 */
@Mapper(componentModel = "spring")
public interface VatRateMapper {
    /**
     * Converts from vat rate entity to vat rate model.
     */
    VatRate toVatRate(VatRateEntity vatRate);
}