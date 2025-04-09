package uk.gov.laa.ccw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.entity.InvoiceEntity;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, String> {

    @NativeQuery(value = "SELECT MAX(INVOICE_NUMBER) FROM "
            + "CCW.INVOICES WHERE "
            + "PROVIDER_ID = ? AND OFFICE_ID = ?")
    Optional<Integer> fetchLastInvoiceNumber(String provider, String office);
}
