package uk.gov.laa.ccw.services.validators;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.laa.ccw.exceptions.MissingDataException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest()
public class MatterCodesValidatorTest {
    @InjectMocks
    private MatterCodesValidator classUnderTest;

    @Test
    void shouldAllowValidRequest() {
        assertDoesNotThrow(() -> classUnderTest.validateRequest("law1"));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenLawTypeBlank() {
        assertThrows(MissingDataException.class,
                () -> classUnderTest.validateRequest(""));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenLawTypeNull() {
        assertThrows(MissingDataException.class,
                () -> classUnderTest.validateRequest(null));
    }
}
