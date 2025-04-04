package uk.gov.laa.ccw.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.gov.laa.ccw.mapper.api.MatterCodesResponseMapper;
import uk.gov.laa.ccw.models.MatterCode;
import uk.gov.laa.ccw.models.api.MatterCodes200ResponseMatterCode;
import uk.gov.laa.ccw.services.MatterCodesService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatterCodesControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    MatterCodesService matterCodesService; // This is required, despite the sonarlint suggestions

    @MockitoBean
    MatterCodesResponseMapper matterCodesResponseMapper;

    @Test
    void shouldReturnAllMatterCode1() throws Exception {
        MatterCode matterCodeOne = MatterCode.builder().matterCodeId("1").description("description").build();
        MatterCode matterCodeTwo = MatterCode.builder().matterCodeId("2").description("description").build();

        String returnedContent = "{\"matterCodes\":[{\"matterCode\":\"1\",\"description\":\"description\"},{\"matterCode\":\"2\",\"description\":\"description\"}]}";
        when(matterCodesService.getAllMatterCodes())
                .thenReturn(List.of(matterCodeOne, matterCodeTwo));
        when(matterCodesResponseMapper.toMatterCodes200ResponseMatterCode(matterCodeOne))
                .thenReturn(MatterCodes200ResponseMatterCode.builder().matterCode("1").description("description").build());
        when(matterCodesResponseMapper.toMatterCodes200ResponseMatterCode(matterCodeTwo))
                .thenReturn(MatterCodes200ResponseMatterCode.builder().matterCode("2").description("description").build());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/matter-codes"))
                .andExpect(status().isOk())
                .andExpect(content().string(returnedContent));
    }

    @Test
    void shouldReturnMatterCode2ForMatterCode1() throws Exception {
        MatterCode matterCodeOne = MatterCode.builder().matterCodeId("1").description("description").build();
        MatterCode matterCodeTwo = MatterCode.builder().matterCodeId("2").description("description").build();

        String returnedContent = "{\"matterCodes\":[{\"matterCode\":\"1\",\"description\":\"description\"},{\"matterCode\":\"2\",\"description\":\"description\"}]}";
        when(matterCodesService.getAllMatterTwosForMatterCodeOne(anyString()))
                .thenReturn(List.of(matterCodeOne, matterCodeTwo));
        when(matterCodesResponseMapper.toMatterCodes200ResponseMatterCode(matterCodeOne))
                .thenReturn(MatterCodes200ResponseMatterCode.builder().matterCode("1").description("description").build());
        when(matterCodesResponseMapper.toMatterCodes200ResponseMatterCode(matterCodeTwo))
                .thenReturn(MatterCodes200ResponseMatterCode.builder().matterCode("2").description("description").build());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/matter-codes/CODE1/matter-code-2"))
                .andExpect(status().isOk())
                .andExpect(content().string(returnedContent));
    }

}
