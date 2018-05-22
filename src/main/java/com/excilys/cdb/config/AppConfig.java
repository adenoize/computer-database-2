package main.java.com.excilys.cdb.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = { "main.java.com.excilys.cdb" })
public class AppConfig {

    static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    /**
     * DataSource bean.
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig("/datasource.properties");
//        LOGGER.debug("Datasource");
        return new HikariDataSource(config);
    }
}