package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dev.portal.models.UserAuthModel;

public class UserAuthDaoClient {

    private MongoTemplate mongoTemplate;

    public UserAuthDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<UserAuthModel> getAllUserAuthModel() {
        return mongoTemplate.findAll(UserAuthModel.class);
    }

    public UserAuthModel getUserAuthModel(String username) {
        return mongoTemplate.find(new Query(Criteria.where("username").is(username)), UserAuthModel.class).get(0);
    }

    public UserAuthModel addOrUpdateUserAuthModel(UserAuthModel userAuthModel) {
        mongoTemplate.save(userAuthModel);
        return getUserAuthModel(userAuthModel.getUsername());
    }

    public Boolean deleteUserAuthModel(UserAuthModel userAuthModel) {
        return mongoTemplate.remove(userAuthModel).getDeletedCount() > 0 ? true : false;
    }
    
    public Boolean deleteUserAuthModelByName(String username) {
        return mongoTemplate.remove(getUserAuthModel(username)).getDeletedCount() > 0 ? true : false;
    }

    //used only for updating self password
    public UserAuthModel updateUserPassword(String username, String password) {
        UserAuthModel userAuthModel = getUserAuthModel(username);
        userAuthModel.setPassword(password);
        return addOrUpdateUserAuthModel(userAuthModel);
    }

}
