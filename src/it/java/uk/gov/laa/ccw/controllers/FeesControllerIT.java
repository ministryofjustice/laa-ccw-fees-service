package uk.gov.laa.ccw.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.gov.laa.ccw.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class FeesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCalculateFees() throws Exception {
        mockMvc
                .perform(get("/v1/fees/calculate").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"locationCode\": \"LDN\", \"caseStage\": \"FPL01\", " +
                                "\"matterCode1\": \"FAMA\", \"matterCode2\": \"FPET\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{"
                        + "\"fees\": [{"
                        + "\"amount\": \"86.00\","
                        + " \"vat\": \"17.20\","
                        + "\"total\": \"103.20\""
                        + "}]}"));
    }

    @Test
    void shouldListAvailableFees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/fees/list-available")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"locationCode\": \"LDN\", \"caseStage\": \"FPL01\", " +
                        "\"matterCode1\": \"FAMA\", \"matterCode2\": \"FPET\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(
                        "{\"fees\":[{\"amount\":86.0,\"levelCode\":\"LVL1\",\"levelCodeType\":\"A\","
                                + "\"description\":\"Level 1\",\"formQuestion\":null}]}"));
    }
}
