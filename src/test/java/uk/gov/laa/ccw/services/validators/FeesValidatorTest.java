package uk.gov.laa.ccw.services.validators;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.models.api.FeeCalculateRequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest()
public class FeesValidatorTest {

    @InjectMocks
    private FeesValidator classUnderTest;

    @Test
    void shouldAllowValidRequest() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("matterCode2")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertDoesNotThrow(() -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode1Blank() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("")
                .matterCode2("matterCode2")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode2Blank() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenCaseStageBlank() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("matterCode2")
                .caseStage("")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenLocationCodeBlank() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("mattercode2")
                .caseStage("casestage")
                .locationCode("")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode1Null() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1(null)
                .matterCode2("matterCode2")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode2Null() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2(null)
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenCaseStageNull() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("matterCode2")
                .caseStage(null)
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenLocationCodeNull() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("mattercode2")
                .caseStage("casestage")
                .locationCode(null)
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

}
