package uk.gov.laa.ccw.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeesCalculatorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldDoubleNumberSuppliedIfDecimal() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/fees/1234.56"))
                .andExpect(status().isOk())
                .andExpect(content().string("2469.12"));
    }

    @Test
    void shouldDoubleNumberSuppliedIfInteger() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/fees/9876"))
                .andExpect(status().isOk())
                .andExpect(content().string("19752.00"));
    }

    @Test
    void shouldErrorIfNotSuppliedNumber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/fees/hi"))
                .andExpect(status().is4xxClientError());
    }

}
