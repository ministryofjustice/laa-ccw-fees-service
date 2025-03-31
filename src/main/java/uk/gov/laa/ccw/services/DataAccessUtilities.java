package uk.gov.laa.ccw.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
public class DataAccessUtilities {
  private static final String ERROR_MESSAGE_FORMAT = "Unable to read file with filePath [%s]";

  public static void initialiseDatabase(JdbcTemplate jdbcTemplate) {
    String sqlSchema = readResourceToString("ccw-database-schema.sql");
    String sqlData = readResourceToString("ccw-database-data.sql");

    jdbcTemplate.execute(sqlSchema);
    jdbcTemplate.execute(sqlData);

  }

  public static String readResourceToString(String filePath) {
    try (InputStream inputStream = DataAccessUtilities.class.getClassLoader().getResourceAsStream(filePath)) {
      if (inputStream == null) {
        throw new RuntimeException(String.format(ERROR_MESSAGE_FORMAT, filePath));
      }
      return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(String.format(ERROR_MESSAGE_FORMAT, filePath), e);
    }
  }
}
