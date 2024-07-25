package com.example.spring_boot_mds.config;

import jakarta.transaction.Transaction;
import jakarta.transaction.TransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.config.JtaTransactionManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        transactionManagerRef = "mySqlTransactionManager",
        basePackages = "com.example.spring_boot_mds.order.repository",
        entityManagerFactoryRef = "mySqLEntityManagerFactoryBean"
)
public class MySqlDatasourceConfiguration {

    @ConfigurationProperties("spring.datasource.mysql")
    @Bean
    public DataSourceProperties mySqlDataSourceProperties(){
        return new DataSourceProperties();
    }


  //  @Primary
    @Bean
    public DataSource mySqlDatasource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUsername(mySqlDataSourceProperties().getUsername());
//        dataSource.setPassword(mySqlDataSourceProperties().getPassword());
//        dataSource.setUrl(mySqlDataSourceProperties().getUrl());
//        dataSource.setDriverClassName(mySqlDataSourceProperties().getDriverClassName());
//        return dataSource;
        return mySqlDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mySqLEntityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder,@Qualifier("mySqlDatasource") DataSource dataSource){
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.example.spring_boot_mds.order.entity")
                .build();
    }

    @Bean
    public PlatformTransactionManager mySqlTransactionManager(@Qualifier("mySqLEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean.getObject()));
    }
}
