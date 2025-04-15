package uk.gov.laa.ccw.mapper.api;

import org.mapstruct.Mapper;
import uk.gov.laa.ccw.model.FeeDetails;
import uk.gov.laa.ccw.model.FeeElement;
import uk.gov.laa.ccw.model.api.FeeCalculate200ResponseFee;
import uk.gov.laa.ccw.model.api.FeeListAvailable200ResponseFee;

/**
 * Maps data into a fee response api.
 */
@Mapper(componentModel = "spring")
public interface FeeResponseMapper {
    /**
     * Maps the given fees and fees list available to a FeeListAvailable200Response.
     *
     * @return the FeeCalculate200Response
     */
    FeeListAvailable200ResponseFee toListAvailableResponse(FeeDetails fee);

    /**
     * Maps the given FeeElement to a FeeCalculate200ResponseFee.
     */
    FeeCalculate200ResponseFee toFeeCalculateResponse(FeeElement feeElement);

}