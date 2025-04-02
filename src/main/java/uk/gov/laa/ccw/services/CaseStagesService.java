package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.dao.CaseStagesDao;
import uk.gov.laa.ccw.models.CaseStage;

import java.util.List;
import java.util.Optional;

/**
 * Service class for the case stages.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CaseStagesService {
    private final CaseStagesDao caseStagesDao;

    /**
     * Gets all the case stages for the given matter code one and matter code two.
     *
     * @param matterCodeOne the matter code one
     * @param matterCodeTwo the matter code two
     * @return the list of case stages
     */
    public List<CaseStage> getAllCaseStagesForMatterCodes(String matterCodeOne,
                                                          String matterCodeTwo) {
        log.info("get case stages");
        List<CaseStage> caseStages = caseStagesDao.fetchCaseStages(matterCodeOne);

        Optional<CaseStage> caseStage = caseStages.stream()
                .filter(c -> c.getCaseStageId().contentEquals("FPL10"))
                .findAny();

        if (caseStage.isPresent()) {
            log.info("case stage FPL10 found so check for FAMA and FPET combination");
            if (!(matterCodeOne.contentEquals("FAMA") && matterCodeTwo.contentEquals("FPET"))) {
                log.info("remove case stage FPL10 found");
                caseStages.remove(caseStage.get());
            }
        }

        return caseStages;
    }
}
