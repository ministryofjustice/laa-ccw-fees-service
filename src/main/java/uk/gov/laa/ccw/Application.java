package uk.gov.laa.ccw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Fees Calculator Service application.
 */
@SpringBootApplication
public class Application {

    /**
     * The application main method.
     *
     * @param args the application arguments.
     */
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}