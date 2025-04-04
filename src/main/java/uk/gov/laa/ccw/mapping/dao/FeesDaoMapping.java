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
        if (queryData.get("PROVIDER_LOCATION") != null) {
            builder.providerLocation(queryData.get("PROVIDER_LOCATION").toString());
        } else {
            builder.providerLocation("");
        }

        if (queryData.get("LEVEL_CODE") != null) {
            builder.levelCode(queryData.get("LEVEL_CODE").toString());
        } else {
            builder.levelCode("");
        }

        if (queryData.get("CASE_STAGE") != null) {
            builder.caseStage(queryData.get("CASE_STAGE").toString());
        } else {
            builder.caseStage("");
        }

        if (queryData.get("AMOUNT") != null) {
            builder.amount(Double.valueOf(queryData.get("AMOUNT").toString()));
        } else {
            builder.amount(0.00);
        }

        return builder.build();
    }

}
