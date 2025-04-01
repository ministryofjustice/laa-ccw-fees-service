package uk.gov.laa.ccw.Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeesHelper {

    public static List<Map<String, Object>> createFeesTestData() {
        List<Map<String, Object>> dataSet = new ArrayList<Map<String, Object>>();
        Map<String, Object> rowSet = new HashMap<String, Object>();
        rowSet.put("AMOUNT", "1.0");
        rowSet.put("CASE_STAGE", "CS1");
        rowSet.put("LEVEL_CODE", "LV1");
        rowSet.put("PROVIDER_LOCATION", "LOC1");
        dataSet.add(rowSet);
        rowSet = new HashMap<String, Object>();
        rowSet.put("AMOUNT", "2.0");
        rowSet.put("CASE_STAGE", "CS1");
        rowSet.put("LEVEL_CODE", "LV2");
        rowSet.put("PROVIDER_LOCATION", "LOC1");
        dataSet.add(rowSet);
        rowSet = new HashMap<String, Object>();
        rowSet.put("AMOUNT", "4.0");
        rowSet.put("CASE_STAGE", "CS1");
        rowSet.put("LEVEL_CODE", "LV3");
        rowSet.put("PROVIDER_LOCATION", "LOC1");
        dataSet.add(rowSet);
        rowSet = new HashMap<String, Object>();
        rowSet.put("AMOUNT", "1.0");
        rowSet.put("CASE_STAGE", "CS2");
        rowSet.put("LEVEL_CODE", "LV1");
        rowSet.put("PROVIDER_LOCATION", "LOC1");
        dataSet.add(rowSet);
        rowSet = new HashMap<String, Object>();
        rowSet.put("AMOUNT", "128.0");
        rowSet.put("CASE_STAGE", "CS1");
        rowSet.put("LEVEL_CODE", "LV1");
        rowSet.put("PROVIDER_LOCATION", "LOC2");
        dataSet.add(rowSet);
        rowSet = new HashMap<String, Object>();
        rowSet.put("AMOUNT", "256.0");
        rowSet.put("CASE_STAGE", "CS1");
        rowSet.put("LEVEL_CODE", "LV2");
        rowSet.put("PROVIDER_LOCATION", "LOC2");
        dataSet.add(rowSet);
        return dataSet;
    }
}
