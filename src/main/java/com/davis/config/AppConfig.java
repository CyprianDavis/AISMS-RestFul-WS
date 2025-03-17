package com.davis.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;

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
     * Creates and configures a LocalContainerEntityManagerFactoryBean for JPA.
     * This bean is responsible for creating JPA EntityManagerFactory instances.
     * 
     * @return LocalContainerEntityManagerFactoryBean - The configured entity manager factory bean.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource()); // Sets the data source for the entity manager.
        em.setPackagesToScan("com.davis.model"); // Specifies the package to scan for JPA entity classes.

        // Use Hibernate as the JPA vendor adapter
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        // Set Hibernate-specific properties
        em.setJpaProperties(hibernateProperties());

        return em; // Returns the configured entity manager factory bean.
    }

    /**
     * Configures Hibernate-specific properties for JPA.
     * 
     * @return Properties - A Properties object containing Hibernate settings.
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties(); // Creates a Properties object.
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect"); // Sets the SQL Server dialect.
        properties.setProperty("hibernate.show_sql", "true"); // Enables logging of SQL statements.
        properties.setProperty("hibernate.format_sql", "true"); // Formats SQL statements for better readability.
        properties.setProperty("hibernate.hbm2ddl.auto", "update"); // Automatically updates the database schema.
        return properties; // Returns the configured properties.
    }

    /**
     * Creates and configures a JpaTransactionManager bean.
     * This bean manages JPA transactions.
     * 
     * @return JpaTransactionManager - The configured transaction manager.
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory); // Sets the entity manager factory.
        return transactionManager; // Returns the configured transaction manager.
    }
}