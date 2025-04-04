package uk.gov.laa.ccw.validators;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.laa.ccw.exceptions.MissingDataException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest()
public class MatterCodesValidatorTest {
    @Test
    void shouldAllowValidRequest() {
        assertDoesNotThrow(() -> MatterCodesValidator.validateRequest("law1"));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenLawTypeBlank() {
        assertThrows(MissingDataException.class,
                () -> MatterCodesValidator.validateRequest(""));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenLawTypeNull() {
        assertThrows(MissingDataException.class,
                () -> MatterCodesValidator.validateRequest(null));
    }

}
