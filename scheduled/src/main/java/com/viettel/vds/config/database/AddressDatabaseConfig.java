package com.viettel.vds.config.database;


import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@ConditionalOnProperty(value = "app.datasource.address.enable", havingValue = "true", matchIfMissing = true)
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "addressEntityManagerFactory",
        transactionManagerRef = "addressTransactionManager", basePackages = {"com.viettel.vds.repository.address"})
public class AddressDatabaseConfig {
    EntityManagerFactoryBuilder builder;
    @Value("${app.datasource.address.url:#{null}}")
    private String urlForLog;

    @Bean(name = "addressDataSourceProperties")
    @ConfigurationProperties("app.datasource.address")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "addressDataSource")
    @ConfigurationProperties(prefix = "app.datasource.address.configuration")
    public DataSource dataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "addressPropertiesHibernate")
    @ConfigurationProperties(prefix = "app.datasource.address.properties")
    public Map<String, String> dataProperties() {
        return new HashMap<>();
    }

    @Bean(name = "addressEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        log.info("DB config addressDataSource: " + urlForLog);

        return builder.dataSource(dataSource()).properties(dataProperties()).packages("com.viettel.vds.entity.address")
                .build();
    }

    @Bean(name = "addressTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("addressEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    @Autowired
    public void setBuilder(EntityManagerFactoryBuilder builder) {
        this.builder = builder;
    }
}
