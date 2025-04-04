package uk.gov.laa.ccw.mapping.dao;

import uk.gov.laa.ccw.models.FeeRecord;

import java.util.Map;

/**
 * Mapping class between query data and FeeRecord.
 */
public class FeesDaoMapping {

    /**
     * Maps the given query data to a FeeRecord.
     *
     * @param queryData the query data
     * @return the FeeRecord
     */
    public static FeeRecord mapAllFees(Map<String, Object> queryData) {
        FeeRecord.FeeRecordBuilder builder = FeeRecord.builder();

        if (queryData.get("LEVEL_CODE") != null) {
            builder.levelCode(queryData.get("LEVEL_CODE").toString());
        } else {
            builder.levelCode("");
        }

        if (queryData.get("DESCRIPTION") != null) {
            builder.description(queryData.get("DESCRIPTION").toString());
        } else {
            builder.description("");
        }

        if (queryData.get("TYPE") != null) {
            builder.levelCodeType(queryData.get("TYPE").toString());
        } else {
            builder.levelCodeType("");
        }

        if (queryData.get("AMOUNT") != null) {
            builder.amount(Double.valueOf(queryData.get("AMOUNT").toString()));
        } else {
            builder.amount(0.00);
        }

        return builder.build();
    }

}
