package uk.gov.laa.ccw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.entity.CaseStagesEntity;

import java.util.List;

/**
 * Repository for case stages.
 */
@Repository
public interface CaseStagesRepository extends JpaRepository<CaseStagesEntity, String> {

    @NativeQuery(value = "SELECT CS.CASE_STAGE_ID, CS.DESCRIPTION FROM "
                    + "CCW.CASE_STAGES CS, CCW.CASE_STAGES_COMBINATIONS CSC WHERE "
                    + "CS.CASE_STAGE_ID = CSC.CASE_STAGES AND CSC.MATTER_CODE_1 = ?")
    List<CaseStagesEntity> findCaseStagesByMatterCodeOne(String caseStageId);

}
