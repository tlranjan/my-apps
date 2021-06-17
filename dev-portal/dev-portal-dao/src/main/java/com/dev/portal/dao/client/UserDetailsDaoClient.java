package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.dev.portal.models.UserDetailsModel;

public class UserDetailsDaoClient {

    private MongoTemplate mongoTemplate;
    private DevPortalClientCommon devPortalClientCommon;

    public UserDetailsDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.devPortalClientCommon = new DevPortalClientCommon();
    }

    public List<UserDetailsModel> getUserDetailsModel() {
        return devPortalClientCommon.getModels(mongoTemplate, UserDetailsModel.class);
    }

    public List<UserDetailsModel> getUserDetailsModelByOrganization(String organization) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, UserDetailsModel.class, "organization", organization);
    }

    public UserDetailsModel getUserDetailsModelById(String id) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, UserDetailsModel.class, "id", id).get(0);
    }

    public UserDetailsModel getUserDetailsModelByName(String name) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, UserDetailsModel.class, "name", name).get(0);
    }

    public UserDetailsModel addOrUpdateUserDetailsModel(UserDetailsModel userDetailsModel) {
        mongoTemplate.save(userDetailsModel);
        return getUserDetailsModelByName(userDetailsModel.getName());
    }

    public Boolean updateUserDetailsModelOrganization(String oldOrganization, String newOrganization) {
        List<UserDetailsModel> userDetailsModelList = getUserDetailsModelByOrganization(oldOrganization);
        for (UserDetailsModel userDetailsModel : userDetailsModelList) {
            userDetailsModel.setOrganization(newOrganization);
            mongoTemplate.save(userDetailsModel);
        }
        return true;
    }

    public Boolean deleteUserDetailsModel(UserDetailsModel userDetailsModel) {
        return devPortalClientCommon.deleteModel(mongoTemplate, UserDetailsModel.class, userDetailsModel);
    }

    public Boolean deleteUserDetailsModelById(String id) {
        return devPortalClientCommon.deleteModelById(mongoTemplate, UserDetailsModel.class, id);
    }

    public Boolean deleteUserDetailsModelByName(String name) {
        return devPortalClientCommon.deleteModelByName(mongoTemplate, UserDetailsModel.class, name);
    }

}
