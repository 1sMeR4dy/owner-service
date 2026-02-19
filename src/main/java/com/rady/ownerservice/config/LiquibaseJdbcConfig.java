package com.rady.ownerservice.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class LiquibaseJdbcConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    DataSourceProperties liquibaseDataSourceProperties() {

        log.info("Liquibase Bean is Created");
        return new DataSourceProperties();
    }

    @Bean
    DataSource liquibaseDataSource(DataSourceProperties liquibaseDataSourceProperties) {
        return liquibaseDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }
}
