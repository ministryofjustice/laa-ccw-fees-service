package uk.gov.laa.ccw.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.mapping.dao.CaseStagesDaoMapping;
import uk.gov.laa.ccw.models.CaseStage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CaseStagesDao {
    private static final String SELECT_CONFIRM_MATTER_CODE_1_SQL =
            "SELECT MATTER_CODE_ID FROM CCW.MATTER_CODES_1 WHERE MATTER_CODE_ID = ?";

    private static final String SELECT_GET_CASE_STAGES_SQL =
            "SELECT CS.CASE_STAGE_ID, CS.DESCRIPTION FROM "+
            "CCW.CASE_STAGES CS, CCW.CASE_STAGES_COMBINATIONS CSC WHERE "+
            "CS.CASE_STAGE_ID = CSC.CASE_STAGES AND CSC.MATTER_CODE_1 = ?";

    private final JdbcTemplate jdbcTemplate;

    public List<CaseStage> fetchCaseStages(String matterCodeOne) {
        log.info("fetch case stages from database for {}", matterCodeOne);
        List<CaseStage> caseStages = new ArrayList<>();
        List<Map<String, Object>> queryResults = new ArrayList<>();

        try {
            if (jdbcTemplate.queryForList(SELECT_CONFIRM_MATTER_CODE_1_SQL, matterCodeOne).isEmpty()) {
                throw new MatterCodeNotFoundException("Unable to find Matter Code " + matterCodeOne);
            }
        } catch (Exception ex) {
            if (ex instanceof MatterCodeNotFoundException) {
                throw ex;
            }
            throw new DatabaseReadException("Unable to retrieve Matter Codes from database: " + ex);
        }

        try {
            queryResults = jdbcTemplate.queryForList(SELECT_GET_CASE_STAGES_SQL, matterCodeOne);

            caseStages = queryResults.stream().map(CaseStagesDaoMapping::mapAllCaseStages).toList();
        } catch (Exception ex) {
            throw new DatabaseReadException("Unable to retrieve case stages from database: " + ex);
        }

        return caseStages;
    }
}
