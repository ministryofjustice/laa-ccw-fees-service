package uk.gov.laa.ccw.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatterCodesControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnAllMatterCode1() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/matter-codes"))
                .andExpect(status().isOk());
    }

}
