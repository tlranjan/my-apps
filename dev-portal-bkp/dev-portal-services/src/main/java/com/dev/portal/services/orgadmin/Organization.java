package com.dev.portal.services.orgadmin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
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
@RequestMapping(AppURN.ORG_ADMIN_USER)
public class Organization {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/getallorganization", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganizationDetailsModel> getAllOrganization() {
        return new OrganizationDetailsDaoClient(mongoTemplate).getAllOrganizationDetailsModel();
    }
    
    @RequestMapping(value = "/getmyorganizations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganizationDetailsModel> getMyOrganizations() {
        return new OrganizationDetailsDaoClient(mongoTemplate).getAllOrganizationDetailsModel(ServiceHelper.getLoggedInUser());
    }
    
    @RequestMapping(value = "/getmyorganizationnames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMyOrganizationNames() {
        List<OrganizationDetailsModel> organizationDetailsModelList =  new OrganizationDetailsDaoClient(mongoTemplate).getAllOrganizationDetailsModel(ServiceHelper.getLoggedInUser());
        List<String> organizationNames = new ArrayList<>();
        for(OrganizationDetailsModel organizationDetailsModel : organizationDetailsModelList){
            organizationNames.add(organizationDetailsModel.getOrganizationName());
        }
        return organizationNames;
    }
    
    @RequestMapping(value = "/getallorganizationnames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllOrganizationNames() {
        List<OrganizationDetailsModel> organizationDetailsModelList = new OrganizationDetailsDaoClient(mongoTemplate).getAllOrganizationDetailsModel();
        List<String> allOrganizationNames = new ArrayList<>();
        for(OrganizationDetailsModel organizationDetailsModel: organizationDetailsModelList){
            allOrganizationNames.add(organizationDetailsModel.getOrganizationName());
        }
        return allOrganizationNames;
    }

    @RequestMapping(value = "/addorganization", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrganizationDetailsModel addOrganization(@RequestBody OrganizationDetailsModel organizationDetailsModel) {
        return new OrganizationDetailsDaoClient(mongoTemplate).addOrganizationDetailsModel(organizationDetailsModel);
    }

    @RequestMapping(value = "/updateorganization", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrganizationDetailsModel updateOrganization(@RequestBody OrganizationDetailsModel organizationDetailsModel) {
        return new OrganizationDetailsDaoClient(mongoTemplate).updateOrganizationDetailsModel(organizationDetailsModel);
    }

    @RequestMapping(value = "/deleteorganization", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteOrganization(@RequestParam String id) {
        return new OrganizationDetailsDaoClient(mongoTemplate).deleteOrganizationDetailsModel(id);
    }

}
