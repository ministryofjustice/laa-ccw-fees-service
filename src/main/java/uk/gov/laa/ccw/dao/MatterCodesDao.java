package uk.gov.laa.ccw.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.mapping.MatterCodesDaoMapping;
import uk.gov.laa.ccw.models.MatterCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MatterCodesDao {

    private static final String SELECT_ALL_MATTER_CODE_1_SQL =
            "SELECT MATTER_CODE_ID, DESCRIPTION FROM CCW.MATTER_CODES_1";

    private final JdbcTemplate ccwJdbc;

    public List<MatterCode> fetchAllMatterCodes() {
        log.info("fetch all Matter Codes from database");
        List<MatterCode> matterCodes = new ArrayList<>();
        List<Map<String, Object>> queryResults = new ArrayList<>();

        try {
            queryResults = ccwJdbc.queryForList(SELECT_ALL_MATTER_CODE_1_SQL);

            matterCodes = queryResults.stream().map(MatterCodesDaoMapping::mapAllMatterCodes).toList();
        } catch (Exception ex) {
            throw new DatabaseReadException("Unable to retrieve Matter Codes from database: " + ex);
        }

        return matterCodes;
    }
}
