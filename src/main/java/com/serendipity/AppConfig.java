package com.serendipity;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public JtsModule jtsModule() {
        return new JtsModule();
    }

//    @Bean
//    public SessionFactory sessionFactory() {
//        StandardServiceRegistry standardRegistry =
//                new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//        Metadata metaData =
//                new MetadataSources(standardRegistry).getMetadataBuilder().build();
//        SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
//
//        return sessionFactory;
//    }
}
