package com.dev.portal.commons;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dev.portal.dao.client.OrganizationDetailsDaoClient;
import com.dev.portal.dao.client.UserAuthDaoClient;
import com.dev.portal.dao.client.UserDetailsDaoClient;
import com.dev.portal.models.OrganizationDetailsModel;
import com.dev.portal.models.UserAuthModel;
import com.dev.portal.models.UserDetailsModel;

public class ServiceHelper {

    public UserAuthModel updateSelfPassword(MongoTemplate mongoTemplate, String password) {
        return new UserAuthDaoClient(mongoTemplate).updateUserPassword(SecurityContextHolder.getContext().getAuthentication().getName(), password);
    }
    
    public String getMyOrganization(MongoTemplate mongoTemplate) {
        UserDetailsDaoClient userDetailsDaoClient = new UserDetailsDaoClient(mongoTemplate);
        UserDetailsModel userDetailsModel = userDetailsDaoClient.getUserDetailsModelByName(SecurityContextHolder.getContext().getAuthentication().getName());
        return userDetailsModel.getOrganization();
    }
    
    public List<String> getMyOrganizations(MongoTemplate mongoTemplate) {
        OrganizationDetailsDaoClient organizationDetailsDaoClient = new OrganizationDetailsDaoClient(mongoTemplate);
        List<OrganizationDetailsModel> organizationDetailsModelList = organizationDetailsDaoClient.getOrganizationDetailsModelByOwner(SecurityContextHolder.getContext().getAuthentication().getName());
        List<String> organizationList = new ArrayList<>();
        for(OrganizationDetailsModel organizationDetailsModel: organizationDetailsModelList){
            organizationList.add(organizationDetailsModel.getName());
        }
        return organizationList;
    }

}
