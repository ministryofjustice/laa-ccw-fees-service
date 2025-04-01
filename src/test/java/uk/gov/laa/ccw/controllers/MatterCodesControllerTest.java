package uk.gov.laa.ccw.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.models.CaseStage;
import uk.gov.laa.ccw.models.MatterCode;
import uk.gov.laa.ccw.services.CaseStagesService;
import uk.gov.laa.ccw.services.MatterCodesService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
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
    CaseStagesService caseStagesService; // This is required, despite the sonarlint suggestions

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

    @Test
    void shouldThrowExceptionWhenNoMatterCode1() throws Exception {
        doThrow(new DatabaseReadException(""){}).when(matterCodesService).getAllMatterCodes();

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/matter-codes"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void shouldReturnMatterCode2ForMatterCode1() throws Exception {
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
        when(matterCodesService.getAllMatterTwosForMatterCodeOne(anyString()))
                .thenReturn(matterCodes);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/matter-codes/CODE1/matter-code-2"))
                .andExpect(status().isOk())
                .andExpect(content().string(returnedContent));
    }

    @Test
    void shouldThrowExceptionWhenNoMatterCode1ForMatterCode2() throws Exception {
        doThrow(new MatterCodeNotFoundException(""){}).when(matterCodesService).getAllMatterTwosForMatterCodeOne(anyString());

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/matter-codes/XXXX/matter-code-2"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldThrowExceptionWhenDatabaseError() throws Exception {
        doThrow(new DatabaseReadException(""){}).when(matterCodesService).getAllMatterTwosForMatterCodeOne(anyString());

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/matter-codes/XXXX/matter-code-2"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void shouldReturnCaseStagesForMatterCode1() throws Exception {
        List<CaseStage> caseStages = List.of(
                CaseStage.builder()
                        .caseStageId("1")
                        .description("description")
                        .build(),
                CaseStage.builder()
                        .caseStageId("2")
                        .description("description")
                        .build()
        );
        String returnedContent = "{\"caseStages\":[{\"caseStage\":\"1\",\"description\":\"description\"},{\"caseStage\":\"2\",\"description\":\"description\"}]}";
        when(caseStagesService.getAllCaseStagesForMatterCodeOne(anyString()))
                .thenReturn(caseStages);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/matter-codes/CODE1/case-stages"))
                .andExpect(status().isOk())
                .andExpect(content().string(returnedContent));
    }

    @Test
    void shouldThrowExceptionWhenNoMatterCode1ForCaseStages() throws Exception {
        doThrow(new MatterCodeNotFoundException(""){}).when(caseStagesService).getAllCaseStagesForMatterCodeOne(anyString());

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/matter-codes/XXXX/case-stages"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldThrowExceptionNotCaseStagesWhenDatabaseError() throws Exception {
        doThrow(new DatabaseReadException(""){}).when(caseStagesService).getAllCaseStagesForMatterCodeOne(anyString());

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/matter-codes/XXXX/case-stages"))
                .andExpect(status().is5xxServerError());
    }
}
