package uk.gov.laa.ccw.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.laa.ccw.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class InvoiceControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateInvoice() throws Exception {
        mockMvc
                .perform(put("/v1/invoices/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"provider\": \"A Provider\", \"office\": \"ABC Office\", " +
                        "\"amount\": 100}"))
                .andExpect(status().isOk());
    }
}
