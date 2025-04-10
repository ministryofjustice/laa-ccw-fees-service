package uk.gov.laa.ccw.mapper.api;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccw.model.MatterCode;
import uk.gov.laa.ccw.model.api.MatterCodes200ResponseMatterCode;

/**
 * Mapper class between MatterCode and MatterCodes200ResponseMatterCode.
 */
@Mapper(componentModel = "spring")
public interface MatterCodesResponseMapper {

    /**
     * Maps the given MatterCode to a MatterCodes200ResponseMatterCode.
     *
     * @param matterCode the matter code
     * @return the MatterCodes200ResponseMatterCode
     */
    @Mapping(target = "matterCode", source = "matterCodeId")
    MatterCodes200ResponseMatterCode toMatterCodes200ResponseMatterCode(MatterCode matterCode);

}
