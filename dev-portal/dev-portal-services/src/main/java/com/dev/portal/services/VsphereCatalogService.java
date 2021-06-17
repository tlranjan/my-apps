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
import com.dev.portal.dao.client.VsphereCatalogDaoClient;
import com.dev.portal.models.VsphereCatalogModel;

@RestController
public class VsphereCatalogService {

	@Autowired
    MongoTemplate mongoTemplate;
	
	@RequestMapping(value = AppURN.ADMIN_USER+"/getallvspherecatalogmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VsphereCatalogModel> getAllVsphereCatalogModel() {
        return new VsphereCatalogDaoClient(mongoTemplate).getVsphereCatalogModel();
    }
    //not used
    @RequestMapping(value = AppURN.ADMIN_USER+"/getvspherecatalogmodelbyendpoint", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VsphereCatalogModel> getVsphereCatalogModelByEndpoint(@RequestParam String endpoint) {
        return new VsphereCatalogDaoClient(mongoTemplate).getVsphereCatalogModelByEndpoint(endpoint);
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorgsvspherecatalogmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VsphereCatalogModel> getMyOrgsVsphereCatalogModel() {
        List<VsphereCatalogModel> vsphereCatalogModelList = new ArrayList<>();
        List<String> organizations = new ServiceHelper().getMyOrganizations(mongoTemplate);
        VsphereCatalogDaoClient vsphereCatalogDaoClient = new VsphereCatalogDaoClient(mongoTemplate);
        for(String organization : organizations){
            vsphereCatalogModelList.addAll(vsphereCatalogDaoClient.getVsphereCatalogModelByOrganization(organization));
        }
        return vsphereCatalogModelList;
    }
    
    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/catalog_admin_getmyorgvspherecatalogmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VsphereCatalogModel> getMyOrgVsphereCatalogModel() {
        return new VsphereCatalogDaoClient(mongoTemplate).getVsphereCatalogModelByOrganization(new ServiceHelper().getMyOrganization(mongoTemplate));
    }
    //not in use
    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/getmyvspherecatalogmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VsphereCatalogModel> getMyVsphereCatalogModel() {
        return new VsphereCatalogDaoClient(mongoTemplate).getVsphereCatalogModelByOwner(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    
    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/addvspherecatalogmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public VsphereCatalogModel addVsphereCatalogModel(@RequestBody VsphereCatalogModel vsphereCatalogModel) {
    	System.out.println("1=====addVsphereCatalogModel");
    	vsphereCatalogModel.setOrganization(new ServiceHelper().getMyOrganization(mongoTemplate));
    	vsphereCatalogModel.setOwner(SecurityContextHolder.getContext().getAuthentication().getName());
        return new VsphereCatalogDaoClient(mongoTemplate).addOrUpdateVsphereCatalogModel(vsphereCatalogModel);
    }

    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/updatevspherecatalogmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public VsphereCatalogModel updateVsphereCatalogModel(@RequestBody VsphereCatalogModel vsphereCatalogModel) {
    	System.out.println("1=====updateVsphereCatalogModel");
        return new VsphereCatalogDaoClient(mongoTemplate).addOrUpdateVsphereCatalogModel(vsphereCatalogModel);
    }

    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/deletevspherecatalogmodel", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteVsphereCatalogModel(@RequestParam String id) {
    	System.out.println("1=====deleteVsphereCatalogModel");
        return new VsphereCatalogDaoClient(mongoTemplate).deleteVsphereCatalogModelById(id);
    }
    
    @RequestMapping(value = AppURN.DEVELOPER_USER+"/getmyorgvspherepublishedcatalogmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VsphereCatalogModel> getMyOrgVspherePublishedCatalogModel() {
        System.out.println("getMyOrgVspherePublishedCatalogModel");
        return new VsphereCatalogDaoClient(mongoTemplate).getVsphereCatalogModelByPublished(new ServiceHelper().getMyOrganization(mongoTemplate));
    }
    
}
