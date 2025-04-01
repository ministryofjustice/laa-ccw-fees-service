package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.dao.CaseStagesDao;
import uk.gov.laa.ccw.models.CaseStage;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaseStagesService {
    private final CaseStagesDao caseStagesDao;

    public List<CaseStage> getAllCaseStagesForMatterCodeOne(String matterCodeOne) {
        log.info("return matter codes from dao to controller");

        return caseStagesDao.fetchCaseStages(matterCodeOne);
    }
}
