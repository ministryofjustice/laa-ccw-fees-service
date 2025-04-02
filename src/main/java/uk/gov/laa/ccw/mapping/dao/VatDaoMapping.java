package uk.gov.laa.ccw.mapping.dao;

import java.util.Map;

/**
 * Mapping class between query data and VAT.
 */
public class VatDaoMapping {

    /**
     * Maps the given query data to VAT.
     *
     * @param queryData the query data
     * @return the VAT
     */
    public static Double map(Map<String, Object> queryData) {
        if (queryData.get("RATE_PERCENTAGE") == null) {
            return 0.0;
        }

        return Double.valueOf(queryData.get("RATE_PERCENTAGE").toString());
    }
}
