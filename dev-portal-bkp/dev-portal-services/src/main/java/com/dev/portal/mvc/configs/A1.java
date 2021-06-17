package com.dev.portal.mvc.configs;

import static com.mongodb.MongoClientOptions.builder;

import java.util.Arrays;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.dev.portal.commons.CertificateHelper;
import com.dev.portal.models.OrganizationDetailsModel;
import com.dev.portal.models.UserAuthModel;
import com.dev.portal.models.UserDetailsModel;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class A1 {

    public static void main(String[] args) {
        MongoTemplate template = new MongoTemplate(new SimpleMongoDbFactory(getMongoClient(), "mangodb"));

        UserAuthModel userAuthModel = new UserAuthModel();
        userAuthModel.setUsername("admin");
        userAuthModel.setPassword("admin");
        template.save(userAuthModel);

        UserDetailsModel userDetailsModel = new UserDetailsModel();
        userDetailsModel.setUsername("admin");
        userDetailsModel.setOrganizationName("default");
        userDetailsModel.setUserRole("ROLE_ADMIN");
        template.save(userDetailsModel);

        OrganizationDetailsModel organizationDetailsModel = new OrganizationDetailsModel();
        organizationDetailsModel.setOrganizationName("default");
        organizationDetailsModel.setOrganizationOwner("admin");
        template.save(organizationDetailsModel);
        
        UserAuthModel userAuthModelOrg = new UserAuthModel();
        userAuthModelOrg.setUsername("org_admin");
        userAuthModelOrg.setPassword("org_admin");
        template.save(userAuthModelOrg);

        UserDetailsModel userDetailsModelOrg = new UserDetailsModel();
        userDetailsModelOrg.setUsername("org_admin");
        userDetailsModelOrg.setOrganizationName("default");
        userDetailsModelOrg.setUserRole("ROLE_ORG_ADMIN");
        template.save(userDetailsModelOrg);

        OrganizationDetailsModel organizationDetailsModelOrg = new OrganizationDetailsModel();
        organizationDetailsModelOrg.setOrganizationName("default");
        organizationDetailsModelOrg.setOrganizationOwner("org_admin");
        template.save(organizationDetailsModelOrg);
        

        System.out.println("done!");
    }

    public static MongoClient getMongoClient() {
        return new MongoClient(new ServerAddress("10.10.10.10", Integer.parseInt("27017")),
                Arrays.asList(MongoCredential.createScramSha1Credential("admin", "admin", "admin".toCharArray())),
                builder().sslEnabled(true).socketFactory(CertificateHelper.validateCert("10.10.10.10"))
                        .sslInvalidHostNameAllowed(true).build());
    }


}
