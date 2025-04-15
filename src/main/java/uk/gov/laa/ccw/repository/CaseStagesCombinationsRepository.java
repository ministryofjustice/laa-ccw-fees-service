package uk.gov.laa.ccw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.entity.CaseStagesCombinationsEntity;

import java.util.List;

/**
 * Repository for case stages.
 */
@Repository
public interface CaseStagesCombinationsRepository extends JpaRepository<CaseStagesCombinationsEntity, String> {
    List<CaseStagesCombinationsEntity> findByMatterCodeOne(String matterCodeOne);
}
