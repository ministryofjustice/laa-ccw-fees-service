package uk.gov.laa.ccw.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.gov.laa.ccw.models.MatterCode;
import uk.gov.laa.ccw.services.MatterCodesService;

import java.util.List;

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

    @Test
    void shouldReturnAllMatterCode1() throws Exception {
        List<MatterCode> matterCodes = List.of(
                MatterCode.builder()
                        .matterCodeId("1")
                        .description("description")
                        .build(),
                MatterCode.builder()
                        .matterCodeId("2")
                        .description("description")
                        .build()
        );
        String returnedContent = "{\"matterCodes\":[{\"matterCode\":\"1\",\"description\":\"description\"},{\"matterCode\":\"2\",\"description\":\"description\"}]}";
        when(matterCodesService.getAllMatterCodes())
                .thenReturn(matterCodes);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/matter-codes"))
                .andExpect(status().isOk())
                .andExpect(content().string(returnedContent));
    }

}
