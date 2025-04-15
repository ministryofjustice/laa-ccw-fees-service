package uk.gov.laa.ccw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.entity.FeesEntity;

import java.util.List;

/**
 * Repository for fees.
 */
@Repository
public interface FeesRepository extends JpaRepository<FeesEntity, String> {

    List<FeesEntity> findByProviderLocationAndCaseStage(String providerLocation, String caseStage);

}
