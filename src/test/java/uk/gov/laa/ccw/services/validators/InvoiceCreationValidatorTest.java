package uk.gov.laa.ccw.services.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.laa.ccw.exceptions.MissingDataException;
import uk.gov.laa.ccw.model.api.InvoiceCreateRequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class InvoiceCreationValidatorTest {
    @InjectMocks
    private InvoiceCreationValidator classUnderTest;

    @Test
    void shouldAllowValidRequest() {
        InvoiceCreateRequest request = InvoiceCreateRequest.builder()
                .amount(1234.50)
                .office("office")
                .provider("provider")
                .build();
        assertDoesNotThrow(() -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMissingOffice() {
        InvoiceCreateRequest request = InvoiceCreateRequest.builder()
                .amount(1234.50)
                .office("")
                .provider("provider")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenNullOffice() {
        InvoiceCreateRequest request = InvoiceCreateRequest.builder()
                .amount(1234.50)
                .office(null)
                .provider("provider")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenMissingProvider() {
        InvoiceCreateRequest request = InvoiceCreateRequest.builder()
                .amount(1234.50)
                .office("office")
                .provider("")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenNullProvider() {
        InvoiceCreateRequest request = InvoiceCreateRequest.builder()
                .amount(1234.50)
                .office("office")
                .provider(null)
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }

    @Test
    void shouldThrowMissingDataExceptionWhenNullAmount() {
        InvoiceCreateRequest request = InvoiceCreateRequest.builder()
                .amount(null)
                .office("office")
                .provider("")
                .build();
        assertThrows(MissingDataException.class, () -> classUnderTest.validateRequest(request));
    }
}
