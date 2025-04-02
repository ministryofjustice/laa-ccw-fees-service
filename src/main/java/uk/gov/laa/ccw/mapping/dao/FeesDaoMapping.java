package uk.gov.laa.ccw.mapping.dao;

import uk.gov.laa.ccw.models.FeeRecord;

import java.util.Map;

public class FeesDaoMapping {
    public static FeeRecord mapAllFees(Map<String, Object> queryData) {
        return new FeeRecord() {{
            if (queryData.get("PROVIDER_LOCATION") != null) {
                setProviderLocation(queryData.get("PROVIDER_LOCATION").toString());
            } else {
                setProviderLocation(null);
            }

            if (queryData.get("LEVEL_CODE") != null) {
                setLevelCode(queryData.get("LEVEL_CODE").toString());
            } else {
                setLevelCode("");
            }

            if (queryData.get("CASE_STAGE") != null) {
                setCaseStage(queryData.get("CASE_STAGE").toString());
            } else {
                setCaseStage("");
            }

            if (queryData.get("AMOUNT") != null) {
                setAmount(Double.valueOf(queryData.get("AMOUNT").toString()));
            } else {
                setAmount(0.00);
            }
        }};
    }

}
