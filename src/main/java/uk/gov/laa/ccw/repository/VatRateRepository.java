package uk.gov.laa.ccw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.entity.VatRateEntity;

import java.util.List;

/**
 * Repository for vat rates.
 */
@Repository
public interface VatRateRepository extends JpaRepository<VatRateEntity, String> {
    List<VatRateEntity> findAll();
}