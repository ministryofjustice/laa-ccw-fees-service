package uk.gov.laa.ccw.validators;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.models.api.FeeRequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest()
public class FeesValidatorTest {
    @Test
    void shouldAllowValidRequest() {
        FeeRequest request = FeeRequest.builder()
                                .matterCode1("matterCode1")
                                .matterCode2("matterCode2")
                                .caseStage("caseStage")
                                .locationCode("locationCode")
                                .build();
        assertDoesNotThrow(() -> FeesValidator.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode1Blank() {
        FeeRequest request = FeeRequest.builder()
                .matterCode1("")
                .matterCode2("matterCode2")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> FeesValidator.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode2Blank() {
        FeeRequest request = FeeRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> FeesValidator.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenCaseStageBlank() {
        FeeRequest request = FeeRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("matterCode2")
                .caseStage("")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> FeesValidator.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenLocationCodeBlank() {
        FeeRequest request = FeeRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("mattercode2")
                .caseStage("casestage")
                .locationCode("")
                .build();
        assertThrows(MissingDataException.class, () -> FeesValidator.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode1Null() {
        FeeRequest request = FeeRequest.builder()
                .matterCode1(null)
                .matterCode2("matterCode2")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> FeesValidator.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode2Null() {
        FeeRequest request = FeeRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2(null)
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> FeesValidator.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenCaseStageNull() {
        FeeRequest request = FeeRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("matterCode2")
                .caseStage(null)
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> FeesValidator.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenLocationCodeNull() {
        FeeRequest request = FeeRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("mattercode2")
                .caseStage("casestage")
                .locationCode(null)
                .build();
        assertThrows(MissingDataException.class, () -> FeesValidator.validateRequest(request));
    }

}
