package uk.gov.laa.ccw.validators;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.models.api.CaseStageRequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest()
public class CaseStageValidatorTest {

    @Test
    void shouldAllowValidRequest() {
        CaseStageRequest request = CaseStageRequest.builder()
                .matterCode1("mt1")
                .matterCode2("mt2")
                .build();

        assertDoesNotThrow(() -> CaseStageValidator.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode1Blank() {
        CaseStageRequest request = CaseStageRequest.builder()
                .matterCode1("")
                .matterCode2("mt2")
                .build();

        assertThrows(MissingDataException.class,
                () -> CaseStageValidator.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode2Blank() {
        CaseStageRequest request = CaseStageRequest.builder()
                .matterCode1("mt1")
                .matterCode2("")
                .build();

        assertThrows(MissingDataException.class,
                () -> CaseStageValidator.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode1Null() {
        CaseStageRequest request = CaseStageRequest.builder()
                .matterCode1(null)
                .matterCode2("mt2")
                .build();

        assertThrows(MissingDataException.class,
                () -> CaseStageValidator.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode2Null() {
        CaseStageRequest request = CaseStageRequest.builder()
                .matterCode1("mt1")
                .matterCode2(null)
                .build();

        assertThrows(MissingDataException.class,
                () -> CaseStageValidator.validateRequest(request));
    }

}
