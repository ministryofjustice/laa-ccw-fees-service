package uk.gov.laa.ccw.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.exceptions.DatabaseReadException;
import uk.gov.laa.ccw.mapping.dao.VatDaoMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Dao class for vat rates.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class VatRatesDao {
    private static final String GET_VAT_SQL =
            "SELECT RATE_PERCENTAGE FROM CCW.VAT_RATES";

    private final JdbcTemplate jdbcTemplate;

    /**
     * Fetches the VAT.
     *
     * @return the VAT.
     */
    public Double fetchVat() {
        log.info("fetch vat from database");
        List<Double> vatData = new ArrayList<>();
        List<Map<String, Object>> queryResults = new ArrayList<>();

        try {
            queryResults = jdbcTemplate.queryForList(GET_VAT_SQL);

            vatData = queryResults.stream().map(VatDaoMapping::map).toList();
        } catch (Exception ex) {
            throw new DatabaseReadException("Unable to retrieve vat from database: " + ex);
        }

        return vatData.getFirst();
    }

}
