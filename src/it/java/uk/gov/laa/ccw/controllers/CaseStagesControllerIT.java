package uk.gov.laa.ccw.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.laa.ccw.Application;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class CaseStagesControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAllCaseStages() throws Exception {
        mockMvc
                .perform(get("/v1/case-stages").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"matterCode1\": \"FAMA\", \"matterCode2\": \"FPET\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.caseStages.*", hasSize(2)))
                .andExpect(jsonPath("$.caseStages[0].caseStage", is("FPL01")))
                .andExpect(jsonPath("$.caseStages[1].caseStage", is("FPL10")));
    }
}
