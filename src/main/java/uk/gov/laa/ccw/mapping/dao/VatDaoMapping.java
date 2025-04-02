package uk.gov.laa.ccw.mapping.dao;

import java.util.Map;

public class VatDaoMapping {
    public static Double map(Map<String, Object> queryData) {
        if (queryData.get("RATE_PERCENTAGE") == null) {
            return 0.0;
        }

        return Double.valueOf(queryData.get("RATE_PERCENTAGE").toString());
    }
}
