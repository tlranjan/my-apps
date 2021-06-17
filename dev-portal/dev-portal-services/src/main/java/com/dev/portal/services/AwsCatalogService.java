package com.dev.portal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.portal.commons.ServiceHelper;
import com.dev.portal.constants.AppURN;
import com.dev.portal.dao.client.AwsCatalogDaoClient;
import com.dev.portal.models.AwsCatalogModel;

@RestController
public class AwsCatalogService {

	@Autowired
    MongoTemplate mongoTemplate;
	
	@RequestMapping(value = AppURN.ADMIN_USER+"/getallawscatalogmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AwsCatalogModel> getAllAwsCatalogModel() {
        return new AwsCatalogDaoClient(mongoTemplate).getAwsCatalogModel();
    }
    //not used
    @RequestMapping(value = AppURN.ADMIN_USER+"/getawscatalogmodelbyendpoint", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AwsCatalogModel> getAwsCatalogModelByEndpoint(@RequestParam String endpoint) {
        return new AwsCatalogDaoClient(mongoTemplate).getAwsCatalogModelByEndpoint(endpoint);
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorgsawscatalogmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AwsCatalogModel> getMyOrgsAwsCatalogModel() {
        List<AwsCatalogModel> awsCatalogModelList = new ArrayList<>();
        List<String> organizations = new ServiceHelper().getMyOrganizations(mongoTemplate);
        AwsCatalogDaoClient awsCatalogDaoClient = new AwsCatalogDaoClient(mongoTemplate);
        for(String organization : organizations){
            awsCatalogModelList.addAll(awsCatalogDaoClient.getAwsCatalogModelByOrganization(organization));
        }
        return awsCatalogModelList;
    }
    
    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/catalog_admin_getmyorgawscatalogmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AwsCatalogModel> getMyOrgAwsCatalogModel() {
        return new AwsCatalogDaoClient(mongoTemplate).getAwsCatalogModelByOrganization(new ServiceHelper().getMyOrganization(mongoTemplate));
    }
    //not in use
    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/getmyawscatalogmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AwsCatalogModel> getMyAwsCatalogModel() {
        return new AwsCatalogDaoClient(mongoTemplate).getAwsCatalogModelByOwner(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    
    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/addawscatalogmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AwsCatalogModel addAwsCatalogModel(@RequestBody AwsCatalogModel awsCatalogModel) {
    	awsCatalogModel.setOrganization(new ServiceHelper().getMyOrganization(mongoTemplate));
    	awsCatalogModel.setOwner(SecurityContextHolder.getContext().getAuthentication().getName());
        return new AwsCatalogDaoClient(mongoTemplate).addOrUpdateAwsCatalogModel(awsCatalogModel);
    }

    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/updateawscatalogmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AwsCatalogModel updateAwsCatalogModel(@RequestBody AwsCatalogModel awsCatalogModel) {
        return new AwsCatalogDaoClient(mongoTemplate).addOrUpdateAwsCatalogModel(awsCatalogModel);
    }

    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/deleteawscatalogmodel", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteAwsCatalogModel(@RequestParam String id) {
        return new AwsCatalogDaoClient(mongoTemplate).deleteAwsCatalogModelById(id);
    }
    
    @RequestMapping(value = AppURN.DEVELOPER_USER+"/getmyorgawspublishedcatalogmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AwsCatalogModel> getMyOrgAwsPublishedCatalogModel() {
        System.out.println("getMyOrgAwsPublishedCatalogModel");
        return new AwsCatalogDaoClient(mongoTemplate).getAwsCatalogModelByPublished(new ServiceHelper().getMyOrganization(mongoTemplate));
    }
 
}
