package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.dao.CaseStagesDao;
import uk.gov.laa.ccw.models.CaseStage;


import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaseStagesService {
    private final CaseStagesDao caseStagesDao;

    public List<CaseStage> getAllCaseStagesForMatterCodes(String matterCodeOne, String matterCodeTwo) {
        log.info("get case stages");
        List<CaseStage> caseStages = caseStagesDao.fetchCaseStages(matterCodeOne);

        Optional<CaseStage> caseFPL10 = caseStages.stream()
                .filter(c -> c.getCaseStageId().contentEquals("FPL10"))
                .findAny();

        if (caseFPL10.isPresent()) {
            log.info("case stage FPL10 found so check for FAMA and FPET combination");
            if (!(matterCodeOne.contentEquals("FAMA") && matterCodeTwo.contentEquals("FPET"))) {
                log.info("remove case stage FPL10 found");
                caseStages.remove(caseFPL10.get());
            }
        }

        return caseStages;
    }
}
