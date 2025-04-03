package uk.gov.laa.ccw.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
import uk.gov.laa.ccw.models.api.CaseStageRequest;
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

        when(caseStagesService.getAllCaseStagesForMatterCodes(anyString(), anyString()))
                .thenReturn(caseStages);

        ObjectWriter objectWriter = new ObjectMapper().writer();
        String caseStageRequest = objectWriter.writeValueAsString(
                CaseStageRequest.builder()
                        .matterCode1("CODE1")
                        .matterCode2("CODE2")
                        .build());

        String returnedContent = "{\"caseStages\":[{\"caseStage\":\"1\",\"description\":\"description\"},{\"caseStage\":\"2\",\"description\":\"description\"}]}";

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/case-stages")
                        .content(caseStageRequest)
                        .contentType("application/json"))

                .andExpect(status().isOk())
                .andExpect(content().string(returnedContent));
    }
/*
    @Test
    void shouldReturn400ErrorForNullMatterCode1() throws Exception {

        ObjectWriter objectWriter = new ObjectMapper().writer();
        String caseStageRequest = objectWriter.writeValueAsString(
                CaseStageRequest.builder()
                        .matterCode1(null)
                        .matterCode2("CODE2")
                        .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/case-stages")
                    .content(caseStageRequest)
                    .contentType("application/json"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("{\"error\":\"No matter code 1 provided\"}"));
    }

    @Test
    void shouldReturn400ErrorForNullMatterCode2() throws Exception {

        ObjectWriter objectWriter = new ObjectMapper().writer();
        String caseStageRequest = objectWriter.writeValueAsString(
                CaseStageRequest.builder()
                        .matterCode1("CODE1")
                        .matterCode2(null)
                        .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/case-stages")
                        .content(caseStageRequest)
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("{\"error\":\"No matter code 2 provided\"}"));
    }
*/
    @Test
    void shouldReturn400ErrorForBlankMatterCode1() throws Exception {

        ObjectWriter objectWriter = new ObjectMapper().writer();
        String caseStageRequest = objectWriter.writeValueAsString(
                CaseStageRequest.builder()
                        .matterCode1("")
                        .matterCode2("CODE2")
                        .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/case-stages")
                        .content(caseStageRequest)
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("{\"error\":\"No matter code 1 provided\"}"));
    }

    @Test
    void shouldReturn400ErrorForBlankMatterCode2() throws Exception {

        ObjectWriter objectWriter = new ObjectMapper().writer();
        String caseStageRequest = objectWriter.writeValueAsString(
                CaseStageRequest.builder()
                        .matterCode1("CODE1")
                        .matterCode2("")
                        .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/case-stages")
                        .content(caseStageRequest)
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("{\"error\":\"No matter code 2 provided\"}"));
    }

    @Test
    void shouldThrowExceptionWhenNoMatterCode1ForCaseStages() throws Exception {
        doThrow(new MatterCodeNotFoundException(""){}).when(caseStagesService).getAllCaseStagesForMatterCodes(anyString(), anyString());

        ObjectWriter objectWriter = new ObjectMapper().writer();
        String caseStageRequest = objectWriter.writeValueAsString(
                CaseStageRequest.builder()
                        .matterCode1("CODE1")
                        .matterCode2("CODE2")
                        .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/case-stages")
                .content(caseStageRequest)
                .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldThrowExceptionNotCaseStagesWhenDatabaseError() throws Exception {
        doThrow(new DatabaseReadException(""){}).when(caseStagesService).getAllCaseStagesForMatterCodes(anyString(), anyString());

        ObjectWriter objectWriter = new ObjectMapper().writer();
        String caseStageRequest = objectWriter.writeValueAsString(
                CaseStageRequest.builder()
                        .matterCode1("CODE1")
                        .matterCode2("CODE2")
                        .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/case-stages")
                .content(caseStageRequest)
                .contentType("application/json"))
                .andExpect(status().is5xxServerError());
    }
}
