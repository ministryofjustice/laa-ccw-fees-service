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
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.model.Invoice;
import uk.gov.laa.ccw.model.api.InvoiceCreateRequest;
import uk.gov.laa.ccw.services.InvoiceService;
import uk.gov.laa.ccw.services.validators.InvoiceCreationValidator;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InvoiceControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    InvoiceService invoiceService;

    @MockitoBean
    InvoiceCreationValidator invoiceCreationValidator;

    @Test
    void shouldThrowMissingDataExceptionWhenValidationFails() throws Exception {

        doThrow(new MissingDataException(""){}).when(invoiceCreationValidator).validateRequest(any(InvoiceCreateRequest.class));

        ObjectWriter objectWriter = new ObjectMapper().writer();
        String invoiceRequest = objectWriter.writeValueAsString(
                InvoiceCreateRequest.builder()
                        .provider("provider")
                        .office("office")
                        .amount(123.45)
                        .build());

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/invoices/create")
                        .content(invoiceRequest)
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }


    @Test
    void shouldReturnInvoice() throws Exception {
        ObjectWriter objectWriter = new ObjectMapper().writer();
        String invoiceRequest = objectWriter.writeValueAsString(
                InvoiceCreateRequest.builder()
                        .provider("provider")
                        .office("office")
                        .amount(123.45)
                        .build());

        String returnedContent =
                "{\"invoiceId\":\"INVOICE\",\"invoiceNumber\":\"2\"}";

        when(invoiceService.createInvoice(anyString(), anyString(), anyDouble()))
            .thenReturn(
                    Invoice.builder()
                        .feeAmount(123.45)
                        .invoiceNumber(2)
                        .invoiceId("INVOICE")
                        .providerId("PROVIDER")
                        .officeId("OFFICE")
                        .build()
            );

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/invoices/create")
                        .content(invoiceRequest)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(returnedContent));
    }
}
