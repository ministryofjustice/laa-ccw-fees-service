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
import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.FeeRequest;

import uk.gov.laa.ccw.services.FeesService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeesControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    FeesService feesService; // This is required, despite the sonarlint suggestions

    @Test
    void shouldReturnFees() throws Exception {
        ObjectWriter objectWriter = new ObjectMapper().writer();
        String feeRequest = objectWriter.writeValueAsString(
                FeeRequest.builder()
                        .matterCode1("MT1")
                        .matterCode2("MT2")
                        .locationCode("LOC1")
                        .caseStage("CASE1")
                        .build());

        String returnedContent =
            "{\"matterCode1\":\"MT1\",\"matterCode2\":\"MT2\",\"locationCode\":\"LOC1\",\"caseStage\":\"CASE1\",\"amount\":2331.0,\"vat\":134.0,\"total\":1234.0}";

        when(feesService.calculateFees(anyString(), anyString()))
                .thenReturn(
                    Fee.builder()
                        .total(1234.00)
                        .vat(134.00)
                        .amount(2331.00)
                        .build()
                    );

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/fees")
                .content(feeRequest)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(returnedContent));
    }
}
