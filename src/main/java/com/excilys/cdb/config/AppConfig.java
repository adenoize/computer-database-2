package main.java.com.excilys.cdb.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
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

        return new HikariDataSource(config);
    }

    /**
     * DataSourceTransactionManager bean.
     * @return DataSourceTransactionManager
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());

    }

    /**
     * ViewResolver bean.
     * @return ViewResolver
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/jsp/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
}