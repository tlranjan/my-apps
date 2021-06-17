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
import com.dev.portal.dao.client.OrganizationDetailsDaoClient;
import com.dev.portal.models.OrganizationDetailsModel;

@RestController
public class OrganizationDetailsService {

	@Autowired
    MongoTemplate mongoTemplate;
	
	@RequestMapping(value = AppURN.ADMIN_USER+"/getallorganizationdetailsmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganizationDetailsModel> getAllOrganizationDetailsModel() {
        return new OrganizationDetailsDaoClient(mongoTemplate).getOrganizationDetailsModel();
    }
    
    @RequestMapping(value = AppURN.ADMIN_USER+"/getallorganizationdetailsnames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getallorganizationdetailsnames() {
        List<OrganizationDetailsModel> organizationDetailsModelList = getAllOrganizationDetailsModel();
        List<String> organizationNames = new ArrayList<>();
        for(OrganizationDetailsModel organizationDetailsModel : organizationDetailsModelList){
            organizationNames.add(organizationDetailsModel.getName());
        }
        return organizationNames;
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorganizationdetailsmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganizationDetailsModel> getmyOrganizationDetailsModel() {
        return new OrganizationDetailsDaoClient(mongoTemplate).getOrganizationDetailsModelByOwner(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/addorganizationdetailsmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrganizationDetailsModel addOrganizationDetailsModel(@RequestBody OrganizationDetailsModel organizationDetailsModel) {
    	organizationDetailsModel.setOwner(SecurityContextHolder.getContext().getAuthentication().getName());
        return new OrganizationDetailsDaoClient(mongoTemplate).addOrUpdateOrganizationDetailsModel(organizationDetailsModel);
    }

    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/updateorganizationdetailsmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrganizationDetailsModel updateOrganizationDetailsModel(@RequestBody OrganizationDetailsModel organizationDetailsModel) {
        return new OrganizationDetailsDaoClient(mongoTemplate).addOrUpdateOrganizationDetailsModel(organizationDetailsModel);
    }

    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/deleteorganizationdetailsmodel", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteOrganizationDetailsModel(@RequestParam String id) {
        return new OrganizationDetailsDaoClient(mongoTemplate).deleteOrganizationDetailsModelById(id);
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorganizationnames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMyOrganizationNames() {
        return new ServiceHelper().getMyOrganizations(mongoTemplate);
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorganization", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMyOrganization() {
        return new ServiceHelper().getMyOrganization(mongoTemplate);
    }
}
