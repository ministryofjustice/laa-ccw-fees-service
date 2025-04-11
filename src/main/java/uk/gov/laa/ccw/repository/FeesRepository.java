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
            "SELECT FF.FEE_ID,FF.AMOUNT,FF.LEVEL_CODE,LC.TYPE,LC.DESCRIPTION, LC.FORM_QUESTION FROM "
                    + "CCW.FIXED_FEES FF, CCW.LEVEL_CODES LC WHERE "
                    + "FF.PROVIDER_LOCATION = ? AND FF.CASE_STAGE = ? AND FF.LEVEL_CODE = LC.LEVEL_CODE_ID")
    List<FeesEntity> findAllByProviderLocationAndCaseStage(String providerLocation, String caseStage);
}