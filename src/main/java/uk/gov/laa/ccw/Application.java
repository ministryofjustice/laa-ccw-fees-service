package uk.gov.laa.ccw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import uk.gov.laa.ccw.services.DataAccessUtilities;

/**
 * Entry point for the Fees Calculator Service application.
 */
@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    /**
     * The application main method.
     *
     * @param args the application arguments.
     */
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    /**
     * The application run method.
     *
     * @param strings the application arguments.
     */
    @Override
    public void run(String... strings) {
        if (activeProfile.contains("local")) {
            DataAccessUtilities.initialiseDatabase(jdbcTemplate);
        }
    }
}