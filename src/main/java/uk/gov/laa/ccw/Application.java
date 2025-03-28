package uk.gov.laa.ccw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import uk.gov.laa.ccw.services.DataAccessUtilities;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    JdbcTemplate ccwJdbc;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        DataAccessUtilities.initialiseDatabase(ccwJdbc);
    }
}