package com.dev.portal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.portal.commons.ServiceHelper;
import com.dev.portal.constants.AppURN;
import com.dev.portal.dao.client.UserAuthDaoClient;
import com.dev.portal.dao.client.UserDetailsDaoClient;
import com.dev.portal.models.UserDetailsModel;
import com.dev.portal.models.contants.UserRoles;

@RestController
public class UserDetailsService {

	@Autowired
    MongoTemplate mongoTemplate;
	
	@RequestMapping(value = AppURN.ADMIN_USER+"/getalluserdetailsmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDetailsModel> getAllUserDetailsModel() {
        return new UserDetailsDaoClient(mongoTemplate).getUserDetailsModel();
    }
	
	@RequestMapping(value = AppURN.ADMIN_USER+"/getallroles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String[] getAllAdminRoles() {
        String ROLE_ = "ROLE_";
        return new String[]{ ROLE_+UserRoles.ADMIN, ROLE_+UserRoles.ORG_ADMIN, ROLE_+UserRoles.CATALOG_ADMIN, ROLE_+UserRoles.DEVELOPER };
    }
	
	@RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorgsuserdetailsmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDetailsModel> getMyOrgsUserDetailsModel() {
        List<UserDetailsModel> userDetailsModelList = new ArrayList<>();
        List<String> organizations = new ServiceHelper().getMyOrganizations(mongoTemplate);
        UserDetailsDaoClient userDetailsDaoClient = new UserDetailsDaoClient(mongoTemplate);
        for(String organization : organizations){
            userDetailsModelList.addAll(userDetailsDaoClient.getUserDetailsModelByOrganization(organization));
        }
        return userDetailsModelList;
    }

    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/updateuserdetailsmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsModel updateUserDetailsModel(@RequestBody UserDetailsModel userDetailsModel) {
        return new UserDetailsDaoClient(mongoTemplate).addOrUpdateUserDetailsModel(userDetailsModel);
    }

    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/deleteuserdetailsmodel", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteUserDetailsModel(@RequestBody UserDetailsModel userDetailsModel) {
        if(new UserDetailsDaoClient(mongoTemplate).deleteUserDetailsModelById(userDetailsModel.getId())){
            return new UserAuthDaoClient(mongoTemplate).deleteUserAuthModelByUsername(userDetailsModel.getName());
        } else {
            return false;
        }
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getallroles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String[] getAllRoles() {
        String ROLE_ = "ROLE_";
        return new String[]{ ROLE_+UserRoles.ORG_ADMIN, ROLE_+UserRoles.CATALOG_ADMIN, ROLE_+UserRoles.DEVELOPER };
    }
	
}
