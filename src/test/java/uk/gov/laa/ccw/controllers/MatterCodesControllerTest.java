package uk.gov.laa.ccw.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.gov.laa.ccw.services.DataAccessUtilities;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatterCodesControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private JdbcTemplate ccwJdbc;

    @BeforeEach
    void setup() {
        DataAccessUtilities.initialiseDatabase(ccwJdbc);
    }

    @AfterEach
    void resetDatabase() {
        DataAccessUtilities.dropDatabase(ccwJdbc);
    }

    @Test
    void shouldReturnAllMatterCode1() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/matter-codes"))
                .andExpect(status().isOk());
    }

}
