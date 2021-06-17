package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dev.portal.models.UserAuthModel;

public class UserAuthDaoClient {

    private MongoTemplate mongoTemplate;
    private DevPortalClientCommon devPortalClientCommon;

    public UserAuthDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.devPortalClientCommon = new DevPortalClientCommon();
    }

    public List<UserAuthModel> getUserAuthModel() {
        return devPortalClientCommon.getModels(mongoTemplate, UserAuthModel.class);
    }

    public UserAuthModel getUserAuthModelById(String id) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, UserAuthModel.class, "id", id).get(0);
    }

    public UserAuthModel getUserAuthModelByUsername(String username) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, UserAuthModel.class, "username", username).get(0);
    }

    public UserAuthModel addOrUpdateUserAuthModel(UserAuthModel userAuthModel) {
        mongoTemplate.save(userAuthModel);
        return getUserAuthModelByUsername(userAuthModel.getUsername());
    }

    public Boolean deleteUserAuthModel(UserAuthModel userAuthModel) {
        return devPortalClientCommon.deleteModel(mongoTemplate, UserAuthModel.class, userAuthModel);
    }

    public Boolean deleteUserAuthModelById(String id) {
        return devPortalClientCommon.deleteModelById(mongoTemplate, UserAuthModel.class, id);
    }

    public Boolean deleteUserAuthModelByUsername(String username) {
    	return mongoTemplate.remove(new Query(Criteria.where("username").is(username)), UserAuthModel.class).getDeletedCount() > 0 ? true : false;
    }

    public UserAuthModel updateUserPassword(String username, String password) {
        UserAuthModel userAuthModel = getUserAuthModelByUsername(username);
        userAuthModel.setPassword(password);
        return getUserAuthModelByUsername(username);
    }

}
