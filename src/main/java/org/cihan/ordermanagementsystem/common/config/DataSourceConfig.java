package org.cihan.ordermanagementsystem.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Value("${SPRING_DATASOURCE_USERNAME:postgres}")
    private String datasourceUsername;

    @Value("${SPRING_DATASOURCE_PASSWORD:postgres}")
    private String datasourcePassword;

    @Value("${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/ocihantask-managementdb}")
    private String datasourceUrl;

    @Bean
    public DataSource dataSource() {
        if ("postgres".equals(datasourceUsername)) {
            return DataSourceBuilder.create()
                    .url(datasourceUrl)
                    .username("postgres")
                    .password("postgres")
                    .driverClassName("org.postgresql.Driver")
                    .build();
        } else {
            return DataSourceBuilder.create()
                    .url(datasourceUrl)
                    .username(datasourceUsername)
                    .password(datasourcePassword)
                    .driverClassName("org.postgresql.Driver")
                    .build();
        }
    }
}
