package uk.gov.laa.ccw.services.validators;

import org.junit.jupiter.api.Test;
import uk.gov.laa.ccw.exceptions.MissingDataException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatterCodesValidatorTest {

    private final MatterCodesValidator classUnderTest = new MatterCodesValidator();

    @Test
    void shouldAllowValidRequest() {
        assertDoesNotThrow(() -> classUnderTest.validateRequest("mt1"));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenRequestIsNull() {
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(null));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenRequestIsEmpty() {
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(""));
    }
}