package com.davis.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Configuration class for setting up the Spring application context.
 * This class configures the data source, Hibernate session factory,
 * and transaction management for the application.
 * @author CYPRIAN DAVIS
 */
@Configuration // Indicates that this class contains Spring configuration definitions.
@EnableTransactionManagement // Enables Spring's annotation-driven transaction management.
@ComponentScan(basePackages = "com.davis") // Scans the specified package for Spring components (e.g., @Service, @Repository).
public class AppConfig {

    /**
     * Creates and configures a HikariDataSource bean for database connectivity.
     * 
     * @return DataSource - The configured HikariDataSource object.
     */
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource(); // Creates a HikariCP data source.
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Sets the SQL Server JDBC driver.
        dataSource.setJdbcUrl("jdbc:sqlserver://CYPRIAN-DAVIS\\ABIGAILMSSQL;integratedSecurity=false;DatabaseName=Automated_Inventory_and_sales_Management_system;"); // Sets the connection URL.
        dataSource.setUsername("CYPRIAN"); // Sets the database username.
        dataSource.setPassword("Cyprian_2025"); // Sets the database password.
        return dataSource; // Returns the configured data source.
    }

    /**
     * Creates and configures a LocalSessionFactoryBean for Hibernate.
     * This bean is responsible for creating Hibernate SessionFactory instances.
     * 
     * @return LocalSessionFactoryBean - The configured session factory bean.
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean(); // Creates a session factory bean.
        sessionFactory.setDataSource(dataSource()); // Sets the data source for the session factory.
        sessionFactory.setPackagesToScan("com.data.model"); // Specifies the package to scan for entity classes.
        sessionFactory.setHibernateProperties(hibernateProperties()); // Sets Hibernate-specific properties.
        return sessionFactory; // Returns the configured session factory bean.
    }

    /**
     * Configures Hibernate-specific properties.
     * 
     * @return Properties - A Properties object containing Hibernate settings.
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties(); // Creates a Properties object.
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect"); // Sets the SQL Server dialect.
        properties.setProperty("hibernate.show_sql", "true"); // Enables logging of SQL statements.
        properties.setProperty("hibernate.format_sql", "true"); // Formats SQL statements for better readability.
        return properties; // Returns the configured properties.
    }

    /**
     * Creates and configures a HibernateTransactionManager bean.
     * This bean manages Hibernate transactions.
     * 
     * @return HibernateTransactionManager - The configured transaction manager.
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory().getObject()); // Creates and returns a transaction manager.
    }
}