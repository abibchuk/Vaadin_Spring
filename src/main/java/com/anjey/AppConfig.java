package com.anjey;

/**
 * Created by Anjey on 15.12.2017.
 */

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan({"com.anjey"})
public class AppConfig {


    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.hbm2ddl.auto", "update");
        prop.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        return prop;
    }

    private DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mariadb://localhost:3306/vaadindb");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("qwerty");
//        Resource initSchema = new ClassPathResource("schema.sql");
//        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
//        DatabasePopulatorUtils.execute(databasePopulator, driverManagerDataSource);
        return driverManagerDataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder =
                new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("com.anjey")
                .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory());
    }


}