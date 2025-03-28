package uk.gov.laa.ccw.services;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.jdbc.core.JdbcTemplate;

public class DataAccessUtilities {
    private static final String ERROR_MESSAGE_FORMAT = "Unable to read file with filePath [%s]";

    public static void initialiseDatabase(JdbcTemplate jdbcTemplate) {
        String sqlSchema = readResourceToString("schema.sql");
        String sqlData = readResourceToString("data.sql");

        jdbcTemplate.execute(sqlSchema);
        jdbcTemplate.execute(sqlData);

    }

    public static String readResourceToString(String filePath) {

        ClassLoader classLoader = DataAccessUtilities.class.getClassLoader();
        URL path = classLoader.getResource(filePath);
        if (path == null) {
            throw new RuntimeException(String.format(ERROR_MESSAGE_FORMAT, filePath));
        }

        File file = new File(path.getFile());
        try {
            return org.apache.commons.io.FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(String.format(ERROR_MESSAGE_FORMAT, filePath));
        }
    }
}
