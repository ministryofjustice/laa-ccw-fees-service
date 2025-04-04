package uk.gov.laa.ccw.mapper.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.models.MatterCode;
import uk.gov.laa.ccw.models.api.MatterCodes200ResponseMatterCode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class MatterCodesResponseMapperTest {

    private final MatterCodesResponseMapper matterCodesResponseMapper = new MatterCodesResponseMapperImpl();

    @Test
    void shouldMapToMatterCodesResponse() {
        MatterCode matterCode = MatterCode.builder().matterCodeId("mt1").build();

        MatterCodes200ResponseMatterCode result = matterCodesResponseMapper.toMatterCodes200ResponseMatterCode(matterCode);

        assertNotNull(result);
        assertEquals("mt1", result.getMatterCode());
        assertEquals("", result.getDescription());
    }
}