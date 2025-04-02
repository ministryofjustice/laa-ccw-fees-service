package uk.gov.laa.ccw.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.mapping.dao.MatterCodesDaoMapping;
import uk.gov.laa.ccw.models.MatterCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Dao class for the matter codes.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class MatterCodesDao {

    private static final String SELECT_ALL_MATTER_CODE_1_SQL =
            "SELECT MATTER_CODE_ID, DESCRIPTION FROM CCW.MATTER_CODES_1 WHERE LAW_TYPE = ?";

    private static final String SELECT_CONFIRM_MATTER_CODE_1_SQL =
            "SELECT MATTER_CODE_ID FROM CCW.MATTER_CODES_1 WHERE MATTER_CODE_ID = ?";

    private static final String SELECT_SPECIFIC_MATTER_CODE_2_SQL =
            "SELECT MT2.MATTER_CODE_ID, MT2.DESCRIPTION FROM "
                    + "CCW.MATTER_CODES_2 MT2, CCW.MATTER_CODES_COMBINATIONS MCC WHERE "
                    + "MT2.MATTER_CODE_ID = MCC.MATTER_CODE_2 AND MCC.MATTER_CODE_1 = ?";

    private final JdbcTemplate jdbcTemplate;

    /**
     * Fetches all the matter codes.
     *
     * @return the list of matter codes
     */
    public List<MatterCode> fetchAllMatterCodes() {
        log.info("fetch all Matter Codes from database");

        try {
            List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(SELECT_ALL_MATTER_CODE_1_SQL, "FAM");

            return queryResults.stream().map(MatterCodesDaoMapping::mapAllMatterCodes).toList();
        } catch (Exception ex) {
            throw new DatabaseReadException("Unable to retrieve Matter Codes from database: " + ex);
        }
    }

    /**
     * Fetches all the matter two codes for the given matter code one.
     *
     * @param matterCodeOne the matter code one
     * @return the list of matter codes
     */
    public List<MatterCode> fetchMatterCodeTwos(String matterCodeOne) {
        log.info("fetch Matter Codes two from database for {}", matterCodeOne);

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
            List<Map<String, Object>> queryResults = jdbcTemplate
                    .queryForList(SELECT_SPECIFIC_MATTER_CODE_2_SQL, matterCodeOne);

            return queryResults.stream().map(MatterCodesDaoMapping::mapAllMatterCodes).toList();
        } catch (Exception ex) {
            throw new DatabaseReadException("Unable to retrieve Matter Codes from database: " + ex);
        }
    }

}
