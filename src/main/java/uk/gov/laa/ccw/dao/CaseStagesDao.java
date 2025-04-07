package uk.gov.laa.ccw.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.exceptions.CaseStagesNotFoundException;
import uk.gov.laa.ccw.mapper.dao.CaseStagesMapper;
import uk.gov.laa.ccw.model.CaseStage;
import uk.gov.laa.ccw.repository.CaseStagesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Dao class for the case stages.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class CaseStagesDao {
    private final CaseStagesRepository repository;
    private final CaseStagesMapper caseStagesMapper;

    /**
     * Fetches all the matter codes.
     *
     * @param matterCodeOne the matter one code
     * @return the list of matter codes
     */
    public List<CaseStage> fetchCaseStages(String matterCodeOne) {
        log.info("fetch case stages from database for {}", matterCodeOne);

        List<CaseStage> caseStages =  repository.findCaseStagesByMatterCodeOne(matterCodeOne).stream()
                .map(caseStagesMapper::toCaseStage)
                .collect(Collectors.toCollection(ArrayList::new));

        if (caseStages.isEmpty()) {
            throw new CaseStagesNotFoundException("Unable to find case stage for matter codes " + matterCodeOne);
        }
        return caseStages;
    }
}