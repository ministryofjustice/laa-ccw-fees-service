package uk.gov.laa.ccw.services.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.model.api.FeeCalculateRequest;
import uk.gov.laa.ccw.model.api.FeeListAvailableRequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class FeeCalculateValidatorTest {

    @InjectMocks
    private FeeCalculateValidator classUnderTest;

    @Test
    void shouldAllowValidCalculateRequest() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("matterCode2")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertDoesNotThrow(() -> classUnderTest.validateFeeCalculateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode1BlankForCalculate() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("")
                .matterCode2("matterCode2")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeCalculateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode2BlankForCalculate() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeCalculateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenCaseStageBlankForCalculate() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("matterCode2")
                .caseStage("")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeCalculateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenLocationCodeBlankForCalculate() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("mattercode2")
                .caseStage("casestage")
                .locationCode("")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeCalculateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode1NullForCalculate() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1(null)
                .matterCode2("matterCode2")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeCalculateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode2NullForCalculate() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2(null)
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeCalculateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenCaseStageNullForCalculate() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("matterCode2")
                .caseStage(null)
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeCalculateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenLocationCodeNullForCalculate() {
        FeeCalculateRequest request = FeeCalculateRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("mattercode2")
                .caseStage("casestage")
                .locationCode(null)
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeCalculateRequest(request));
    }

    @Test
    void shouldAllowValidCListAvailableRequest() {
        FeeListAvailableRequest request = FeeListAvailableRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("matterCode2")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertDoesNotThrow(() -> classUnderTest.validateFeeListAvailableRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode1BlankForListAvailable() {
        FeeListAvailableRequest request = FeeListAvailableRequest.builder()
                .matterCode1("")
                .matterCode2("matterCode2")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeListAvailableRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode2BlankForListAvailable() {
        FeeListAvailableRequest request = FeeListAvailableRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeListAvailableRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenCaseStageBlankForListAvailable() {
        FeeListAvailableRequest request = FeeListAvailableRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("matterCode2")
                .caseStage("")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeListAvailableRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenLocationCodeBlankForListAvailable() {
        FeeListAvailableRequest request = FeeListAvailableRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("mattercode2")
                .caseStage("casestage")
                .locationCode("")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeListAvailableRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode1NullForListAvailable() {
        FeeListAvailableRequest request = FeeListAvailableRequest.builder()
                .matterCode1(null)
                .matterCode2("matterCode2")
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeListAvailableRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMatterCode2NullForListAvailable() {
        FeeListAvailableRequest request = FeeListAvailableRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2(null)
                .caseStage("caseStage")
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeListAvailableRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenCaseStageNullForListAvailable() {
        FeeListAvailableRequest request = FeeListAvailableRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("matterCode2")
                .caseStage(null)
                .locationCode("locationCode")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeListAvailableRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenLocationCodeNullForListAvailable() {
        FeeListAvailableRequest request = FeeListAvailableRequest.builder()
                .matterCode1("matterCode1")
                .matterCode2("mattercode2")
                .caseStage("casestage")
                .locationCode(null)
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateFeeListAvailableRequest(request));
    }
}