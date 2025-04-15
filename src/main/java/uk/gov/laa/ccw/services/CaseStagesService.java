package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.exceptions.CaseStagesNotFoundException;
import uk.gov.laa.ccw.mapper.dao.CaseStagesMapper;
import uk.gov.laa.ccw.model.CaseStage;
import uk.gov.laa.ccw.repository.CaseStagesCombinationsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for handling the case stages requests.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CaseStagesService {
    private final CaseStagesCombinationsRepository caseStagesCombinationsRepository;
    private final CaseStagesMapper caseStagesMapper;

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

        List<CaseStage> caseStages =  caseStagesCombinationsRepository
                .findByMatterCodeOne(matterCodeOne).stream()
                .map(caseStagesMapper::toCaseStage)
                .collect(Collectors.toList());

        if (caseStages.isEmpty()) {
            throw new CaseStagesNotFoundException("Unable to find case stage for matter codes " + matterCodeOne);
        }

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