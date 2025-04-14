package uk.gov.laa.ccw.services.validators;

import org.junit.jupiter.api.Test;
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.model.api.CaseStagesRequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CaseStagesValidatorTest {

    private final CaseStagesValidator classUnderTest = new CaseStagesValidator();

    @Test
    void shouldAllowValidFeeListAvailableRequest() {
        CaseStagesRequest request = CaseStagesRequest.builder()
                .matterCode1("mt1")
                .matterCode2("mt2")
                .build();

        assertDoesNotThrow(() -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode1IsNull() {
        CaseStagesRequest request = CaseStagesRequest.builder()
                .matterCode2("mt2")
                .build();

        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode1IsEmpty() {
        CaseStagesRequest request = CaseStagesRequest.builder()
                .matterCode1("")
                .matterCode2("mt2")
                .build();

        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode2IsNull() {
        CaseStagesRequest request = CaseStagesRequest.builder()
                .matterCode1("mt1")
                .build();

        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode2IsEmpty() {
        CaseStagesRequest request = CaseStagesRequest.builder()
                .matterCode1("mt1")
                .matterCode2("")
                .build();

        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }
}