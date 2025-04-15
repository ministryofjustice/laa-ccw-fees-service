package uk.gov.laa.ccw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.entity.MatterCodesOneEntity;

import java.util.List;

/**
 * Repository for matter codes.
 */
@Repository
public interface MatterCodesRepository extends JpaRepository<MatterCodesOneEntity, String> {

    List<MatterCodesOneEntity> findByLawType(String lawType);
}