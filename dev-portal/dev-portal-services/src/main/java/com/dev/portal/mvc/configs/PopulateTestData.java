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

public class PopulateTestData {

    public static void main(String[] args) {
        
        MongoTemplate template = new MongoTemplate(new SimpleMongoDbFactory(getMongoClient(), "mangodb"));

        UserAuthModel userAuthModelAdmin = new UserAuthModel();
        userAuthModelAdmin.setUsername("admin");
        userAuthModelAdmin.setPassword("admin");
        template.save(userAuthModelAdmin);
        
        UserAuthModel userAuthModelOrgAdmin = new UserAuthModel();
        userAuthModelOrgAdmin.setUsername("org_admin");
        userAuthModelOrgAdmin.setPassword("org_admin");
        template.save(userAuthModelOrgAdmin);
        
        UserAuthModel userAuthModelCatalogAdmin = new UserAuthModel();
        userAuthModelCatalogAdmin.setUsername("catalog_admin");
        userAuthModelCatalogAdmin.setPassword("catalog_admin");
        template.save(userAuthModelCatalogAdmin);
        
        UserAuthModel userAuthModelDeveloper = new UserAuthModel();
        userAuthModelDeveloper.setUsername("developer");
        userAuthModelDeveloper.setPassword("developer");
        template.save(userAuthModelDeveloper);
        
        OrganizationDetailsModel organizationDetailsModelAdmin = new OrganizationDetailsModel();
        organizationDetailsModelAdmin.setName("default_admin");
        organizationDetailsModelAdmin.setOwner("admin");
        template.save(organizationDetailsModelAdmin);
        
        OrganizationDetailsModel organizationDetailsModelOrgAdmin = new OrganizationDetailsModel();
        organizationDetailsModelOrgAdmin.setName("default_org_admin");
        organizationDetailsModelOrgAdmin.setOwner("org_admin");
        template.save(organizationDetailsModelOrgAdmin);

        UserDetailsModel userDetailsModelAdmin = new UserDetailsModel();
        userDetailsModelAdmin.setName("admin");
        userDetailsModelAdmin.setOrganization("default_admin");
        userDetailsModelAdmin.setRole("ROLE_ADMIN");
        template.save(userDetailsModelAdmin);
        
        UserDetailsModel userDetailsModelOrgAdmin = new UserDetailsModel();
        userDetailsModelOrgAdmin.setName("org_admin");
        userDetailsModelOrgAdmin.setOrganization("default_org_admin");
        userDetailsModelOrgAdmin.setRole("ROLE_ORG_ADMIN");
        template.save(userDetailsModelOrgAdmin);
        
        UserDetailsModel userDetailsModelCatalogAdmin = new UserDetailsModel();
        userDetailsModelCatalogAdmin.setName("catalog_admin");
        userDetailsModelCatalogAdmin.setOrganization("default_org_admin");
        userDetailsModelCatalogAdmin.setRole("ROLE_CATALOG_ADMIN");
        template.save(userDetailsModelCatalogAdmin);
        
        UserDetailsModel userDetailsModelDeveloper = new UserDetailsModel();
        userDetailsModelDeveloper.setName("developer");
        userDetailsModelDeveloper.setOrganization("default_org_admin");
        userDetailsModelDeveloper.setRole("ROLE_DEVELOPER");
        template.save(userDetailsModelDeveloper);

        System.out.println("done!");
        
    }

    public static MongoClient getMongoClient() {
        return new MongoClient(new ServerAddress("10.10.10.10", Integer.parseInt("27017")),
                Arrays.asList(MongoCredential.createScramSha1Credential("admin", "admin", "admin".toCharArray())),
                builder().sslEnabled(true).socketFactory(CertificateHelper.validateCert("10.10.10.10"))
                        .sslInvalidHostNameAllowed(true).build());
    }


}
