package com.pankaj.multipledb.admissions;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "admissionsTransactionManager"
)
//@Profile("admissions")
public class AdmissionDBConfiguration {
    @Primary
    @Bean(name="datasource")
    @ConfigurationProperties(prefix = "spring.admissions.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("datasource") DataSource dataSource){
        Map<String, Object> admissionDBProperties = new HashMap<>();
        admissionDBProperties.put("hibernate.hbm2ddl.auto", "update");
        admissionDBProperties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .properties(admissionDBProperties)
                .packages("com.pankaj.multipledb.admissions")
                .persistenceUnit("Admission")
                .build();
    }

    @Primary
    @Bean(name = "admissionsTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

}
