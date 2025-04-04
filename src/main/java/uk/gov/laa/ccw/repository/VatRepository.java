package uk.gov.laa.ccw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.entity.VatEntity;

/**
 * Repository for vat.
 */
@Repository
public interface VatRepository  extends JpaRepository<VatEntity, Double> {
}
