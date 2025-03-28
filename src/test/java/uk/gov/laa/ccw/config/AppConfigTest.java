package uk.gov.laa.ccw.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
public class AppConfigTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void shouldCreateDataSource() {
        var dataSource = applicationContext.getBean("ccwDataSource", DataSource.class);
        assertNotNull(dataSource);
    }

    @Test
    void shouldCreateJdbcTemplate() {
        var jdbcTemplate = applicationContext.getBean("ccwJdbc", JdbcTemplate.class);

        assertNotNull(jdbcTemplate);
    }

    @Test
    void shouldJdbcTemplateUseCorrectDataSource() {
        var jdbcTemplate = applicationContext.getBean("ccwJdbc", JdbcTemplate.class);

        assertInstanceOf(DriverManagerDataSource.class, jdbcTemplate.getDataSource());
    }

    @Test
    void shouldCreateInstanceOfDataSourceBean() {
        var dataSource = applicationContext.getBean("ccwDataSource", DataSource.class);

        assertNotNull(dataSource);
        assertInstanceOf(DriverManagerDataSource.class, dataSource);
    }

}
