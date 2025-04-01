package uk.gov.laa.ccw.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.models.CaseStage;
import uk.gov.laa.ccw.services.CaseStagesService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CaseStagesControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CaseStagesService caseStagesService; // This is required, despite the sonarlint suggestions

    @Test
    void shouldReturnCaseStagesForMatterCodes() throws Exception {
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
        when(caseStagesService.getAllCaseStagesForMatterCodes(anyString(), anyString()))
                .thenReturn(caseStages);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/case-stages/CODE1/CODE2"))
                .andExpect(status().isOk())
                .andExpect(content().string(returnedContent));
    }

    @Test
    void shouldReturnErrorForMissingMatterCode2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/case-stages/CODE1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldReturnErrorForMissingMatterCodes() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/case-stages"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldThrowExceptionWhenNoMatterCode1ForCaseStages() throws Exception {
        doThrow(new MatterCodeNotFoundException(""){}).when(caseStagesService).getAllCaseStagesForMatterCodes(anyString(), anyString());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/case-stages/CODE1/CODE2"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldThrowExceptionNotCaseStagesWhenDatabaseError() throws Exception {
        doThrow(new DatabaseReadException(""){}).when(caseStagesService).getAllCaseStagesForMatterCodes(anyString(), anyString());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/case-stages/CODE1/CODE2"))
                .andExpect(status().is5xxServerError());
    }
}
