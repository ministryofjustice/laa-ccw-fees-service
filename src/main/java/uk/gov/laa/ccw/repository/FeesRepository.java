package uk.gov.laa.ccw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.entity.FeesEntity;

import java.util.List;

/**
 * Repository for fees.
 */
@Repository
public interface FeesRepository extends JpaRepository<FeesEntity, String> {

    @NativeQuery(value =
            "SELECT V.FEE_ID, V.AMOUNT, V.LEVEL_CODE, V.TYPE, V.DESCRIPTION, "
            + "T.TEXT FROM CCW.VW_LEVEL_CODE_FEES V "
            + "LEFT JOIN CCW.TEXT_RESOURCES T ON "
            + "T.RESOURCE_ID = CONCAT(V.LEVEL_CODE, '_FQ') "
            + "WHERE V.PROVIDER_LOCATION = ? AND V.CASE_STAGE = ?")
    List<FeesEntity> findAllByProviderLocationAndCaseStage(String providerLocation, String caseStage);
}
