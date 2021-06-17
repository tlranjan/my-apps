package com.dev.portal.mvc.configs;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.dev.portal.commons.CommonUtils;

@Configuration
public class ServiceContextListener implements ServletContextListener {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void contextDestroyed(ServletContextEvent context) {
        mongoTemplate.getMongoDbFactory().getLegacyDb().getMongo().close();
        System.gc();
        CommonUtils.sleepSeconds(5);
        java.beans.Introspector.flushCaches();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    }
}