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
public class MatterCodesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAllMatterCodesForGivenLawType() throws Exception {
        mockMvc
                .perform(get("/v1/matter-codes/FAM"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.matterCodes.*", hasSize(3)))
                .andExpect(jsonPath("$.matterCodes[0].matterCode", is("FAMA")))
                .andExpect(jsonPath("$.matterCodes[1].matterCode", is("FAMC")))
                .andExpect(jsonPath("$.matterCodes[2].matterCode", is("FAMD")));
    }
}
