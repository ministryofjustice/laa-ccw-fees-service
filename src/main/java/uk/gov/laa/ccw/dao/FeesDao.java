package uk.gov.laa.ccw.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.mapping.dao.FeesDaoMapping;
import uk.gov.laa.ccw.models.FeeRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Dao class for fees.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class FeesDao {
    private static final String GET_FEES_BY_LOCATION_CASE_STAGE_SQL =
            "SELECT FF.AMOUNT,FF.LEVEL_CODE,LC.TYPE,LC.DESCRIPTION FROM "+
            "CCW.FIXED_FEES FF, CCW.LEVEL_CODES LC WHERE "+
            "FF.PROVIDER_LOCATION = ? AND FF.CASE_STAGE = ? AND FF.LEVEL_CODE = LC.LEVEL_CODE_ID";

    private final JdbcTemplate jdbcTemplate;

    /**
     * Fetches the fees for a given location.
     *
     * @param providerLocation the provider location
     * @return the list of matter codes
     */
    public List<FeeRecord> fetchFeesForLocationAndCaseStage(
            String providerLocation,
            String caseStage) {
        log.info("fetch fees from database for {}", providerLocation);
        List<FeeRecord> feeData = new ArrayList<>();
        List<Map<String, Object>> queryResults = new ArrayList<>();

        try {
            queryResults = jdbcTemplate.queryForList(GET_FEES_BY_LOCATION_CASE_STAGE_SQL,
                    providerLocation,
                    caseStage);

            feeData = queryResults.stream().map(FeesDaoMapping::mapAllFees).toList();
        } catch (Exception ex) {
            throw new DatabaseReadException("Unable to retrieve fees from database: " + ex);
        }

        return feeData;
    }
}
