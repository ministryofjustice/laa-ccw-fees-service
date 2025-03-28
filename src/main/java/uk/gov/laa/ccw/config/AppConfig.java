package uk.gov.laa.ccw.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    @ConfigurationProperties(prefix = "ccw.datasource")
    DataSource ccwDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean
    JdbcTemplate myJdbc(@Qualifier("ccwDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
