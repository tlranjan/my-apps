package com.dev.portal.mvc.configs;

import static com.mongodb.MongoClientOptions.builder;

import java.util.Arrays;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.dev.portal.commons.CertificateHelper;
import com.dev.portal.dao.client.AwsCatalogDaoClient;
import com.dev.portal.dao.client.UserDetailsDaoClient;
import com.dev.portal.dao.client.VsphereCatalogDaoClient;
import com.dev.portal.models.AwsCatalogModel;
import com.dev.portal.models.AwsEndpointModel;
import com.dev.portal.models.MachineModel;
import com.dev.portal.models.RequestModel;
import com.dev.portal.models.UserDetailsModel;
import com.dev.portal.models.VsphereCatalogModel;
import com.dev.portal.models.VsphereEndpointModel;
import com.dev.portal.models.contants.PlatformType;
import com.dev.portal.models.contants.Published;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class A1 {
    public static void main(String[] args) {
    	
    	MongoTemplate template = new MongoTemplate(new SimpleMongoDbFactory(getMongoClient(), "mongodb"));
    	
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
