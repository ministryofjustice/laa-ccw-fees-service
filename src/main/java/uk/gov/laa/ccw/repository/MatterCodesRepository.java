package uk.gov.laa.ccw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.entity.MatterCodesOneEntity;
import uk.gov.laa.ccw.entity.MatterCodesTwoEntity;

import java.util.List;

/**
 * Repository for matter codes.
 */
@Repository
public interface MatterCodesRepository extends JpaRepository<MatterCodesOneEntity, String> {

    List<MatterCodesOneEntity> findByLawType(String lawType);

    @NativeQuery(value = "SELECT MT2.MATTER_CODE_ID, MT2.DESCRIPTION FROM "
            +  "CCW.MATTER_CODES_2 MT2, CCW.MATTER_CODES_COMBINATIONS MCC WHERE "
            +  "MT2.MATTER_CODE_ID = MCC.MATTER_CODE_2 AND MCC.MATTER_CODE_1 = ?")
    List<MatterCodesTwoEntity> findMatterCodesTwosByMatterCodeOne(String matterCodeOne);
}
