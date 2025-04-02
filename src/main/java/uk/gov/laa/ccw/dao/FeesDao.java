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

@Slf4j
@Repository
@RequiredArgsConstructor
public class FeesDao {
    private static final String GET_FEES_SQL =
        "SELECT AMOUNT,CASE_STAGE,LEVEL_CODE,PROVIDER_LOCATION FROM CCW.FIXED_FEES WHERE PROVIDER_LOCATION = ?";

    private final JdbcTemplate jdbcTemplate;

    public List<FeeRecord> fetchFeesForLocation(String providerLocation) {
        log.info("fetch fees from database for {}", providerLocation);
        List<FeeRecord> feeData = new ArrayList<>();
        List<Map<String, Object>> queryResults = new ArrayList<>();

        try {
            queryResults = jdbcTemplate.queryForList(GET_FEES_SQL, providerLocation);

            feeData = queryResults.stream().map(FeesDaoMapping::mapAllFees).toList();
        } catch (Exception ex) {
            throw new DatabaseReadException("Unable to retrieve fees from database: " + ex);
        }

        return feeData;
    }
}
