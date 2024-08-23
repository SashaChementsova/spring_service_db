package org.chementsova;

import org.chementsova.model.*;
import org.hibernate.SessionFactory;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan
@PropertySource("classpath://../config.properties")
@EnableTransactionManagement
public class DataConfig {

    @Value("${db.host}")
    private String dbHost;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.driver}")
    private String dbDriver;

    @Bean
    public DataSource dataSource() {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();

        dataSource.setUser(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setURL(dbHost);

        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        Properties props = new Properties();
        props.put("hibernate.driver", dbDriver);
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.show_sql", "true");
        sessionBuilder.addProperties(props);
        sessionBuilder.addAnnotatedClass(Employee.class);
        sessionBuilder.addAnnotatedClass(Department.class);
        sessionBuilder.addAnnotatedClass(Ticket.class);
        return sessionBuilder.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
