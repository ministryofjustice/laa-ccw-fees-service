package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.entity.InvoiceEntity;
import uk.gov.laa.ccw.exceptions.InvoiceException;
import uk.gov.laa.ccw.mapper.dao.InvoiceMapper;
import uk.gov.laa.ccw.model.Invoice;
import uk.gov.laa.ccw.repository.InvoiceRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public Invoice CreateInvoice(String provider, String office, Double amount) {
        log.info("create invoice");

        Invoice result = null;

        int nextInvoiceNumber = 1;

        Optional<Integer> lastInvoice = invoiceRepository
                .fetchLastInvoiceNumber(
                    provider,
                    office
                );

        if (lastInvoice.isPresent()) {
            nextInvoiceNumber = lastInvoice.get() + 1;
        }

        try {
            InvoiceEntity e = invoiceRepository.save(
                InvoiceEntity.builder()
                        .providerId(provider)
                        .officeId(office)
                        .invoiceNumber(nextInvoiceNumber)
                        .feeAmount(amount)
                        .invoiceDate(LocalDateTime.now())
                        .invoiceId(UUID.randomUUID().toString())
                .build()
            );

            result = invoiceMapper.toInvoice(e);
        } catch (Exception ex) {
            throw new InvoiceException(ex.getMessage());
        }

        return result;
    }
}
