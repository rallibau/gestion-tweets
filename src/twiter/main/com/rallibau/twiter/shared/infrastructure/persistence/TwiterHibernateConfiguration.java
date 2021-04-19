package com.rallibau.twiter.shared.infrastructure.persistence;

import com.rallibau.shared.infrastructure.config.Parameter;
import com.rallibau.shared.infrastructure.config.ParameterNotExist;
import com.rallibau.shared.infrastructure.hibernate.HibernateConfigurationFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableTransactionManagement
public class TwiterHibernateConfiguration {
    private final HibernateConfigurationFactory factory;
    private final Parameter config;
    private final String CONTEXT_NAME = "twiter";

    public TwiterHibernateConfiguration(HibernateConfigurationFactory factory, Parameter config) {
        this.factory = factory;
        this.config = config;
    }

    @Bean("products-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() throws IOException, ParameterNotExist {
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("products-session_factory")
    public LocalSessionFactoryBean sessionFactory() throws IOException, ParameterNotExist {
        return factory.sessionFactory(CONTEXT_NAME, dataSource());
    }

    @Bean("products-data_source")
    public DataSource dataSource() throws IOException, ParameterNotExist {
        return factory.dataSource(
            config.get("PRODUCTS_DATABASE_HOST"),
            config.getInt("PRODUCTS_DATABASE_PORT"),
            config.get("PRODUCTS_DATABASE_NAME"),
            config.get("PRODUCTS_DATABASE_USER"),
            config.get("PRODUCTS_DATABASE_PASSWORD")
        );
    }
}
