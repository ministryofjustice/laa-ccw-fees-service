package uk.gov.laa.ccw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.entity.MatterCodeCombinationsEntity;

import java.util.List;

/**
 * Repository for matter codes.
 */
@Repository
public interface MatterCodeCombinationsRepository extends JpaRepository<MatterCodeCombinationsEntity, String> {

    List<MatterCodeCombinationsEntity> findAllByMatterCode1(String matterCodeOne);
}