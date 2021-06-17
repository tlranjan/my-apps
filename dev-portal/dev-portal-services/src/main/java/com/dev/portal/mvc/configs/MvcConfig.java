package com.dev.portal.mvc.configs;

import static com.mongodb.MongoClientOptions.builder;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dev.portal.commons.CertificateHelper;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@SuppressWarnings("deprecation")
@EnableWebMvc
@PropertySource({ "classpath:mongodb.properties" })
@ComponentScan(basePackages = "com.dev.portal.services.**")
public class MvcConfig extends WebMvcConfigurerAdapter {

    private @Value("${db.ip}") String db_ip;
    private @Value("${db.port}") String db_port;
    private @Value("${db.name}") String db_name;
    private @Value("${db.user}") String db_user;
    private @Value("${db.password}") String db_password;
    private @Value("${db.admin}") String db_admin;

    @Bean
    public AppUserDetailsService getAppUserDetailsService() {
        return new AppUserDetailsService();
    }

    @Bean
    public MongoTemplate getMongoTemplate() {
        System.out.println("mongo bean==============");
        MongoClient mongoClient =
                new MongoClient(new ServerAddress(db_ip, Integer.parseInt(db_port)),
                        Arrays.asList(MongoCredential.createScramSha1Credential(db_user, db_admin,
                                db_password.toCharArray())),
                        builder().sslEnabled(true).socketFactory(CertificateHelper.validateCert(db_ip))
                                .sslInvalidHostNameAllowed(true).build());
        return new MongoTemplate(new SimpleMongoDbFactory(mongoClient, db_name));
    }


    //    @Bean
    //    public MongeezRunner getMongeezRunner(){
    //        MongoClient mongoClient =
    //                new MongoClient(new ServerAddress(db_ip, Integer.parseInt(db_port)),
    //                        Arrays.asList(MongoCredential.createScramSha1Credential(db_user, db_admin,
    //                                db_password.toCharArray())),
    //                        builder().sslEnabled(true).socketFactory(CertificateHelper.validateCert(db_ip))
    //                                .sslInvalidHostNameAllowed(true).build());
    //        MongeezRunner mongeezRunner = new MongeezRunner();
    //        mongeezRunner.setMongo(mongoClient);
    //        mongeezRunner.setDbName(db_name);
    //        mongeezRunner.setFile(new ClassPathResource("dbscripts/mongeez-schema.xml"));
    //        return mongeezRunner;
    //    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login.html");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }

}
