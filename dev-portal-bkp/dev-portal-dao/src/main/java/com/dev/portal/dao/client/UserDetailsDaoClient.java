package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dev.portal.models.UserDetailsModel;

public class UserDetailsDaoClient {

    private MongoTemplate mongoTemplate;

    public UserDetailsDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public UserDetailsModel getUserDetailsModelById(String id) {
        return new DevPortalClientCommon().getModelById(mongoTemplate, UserDetailsModel.class, id);
    }

    public List<UserDetailsModel> getAllUserDetailsModel() {
        return mongoTemplate.findAll(UserDetailsModel.class);
    }
    
    public UserDetailsModel getUserDetailsModelByUsername(String username) {
        return mongoTemplate.find(new Query(Criteria.where("username").is(username)), UserDetailsModel.class).get(0);
    }

    public UserDetailsModel addOrUpdateUserDetailsModel(UserDetailsModel userDetailsModel) {
        mongoTemplate.save(userDetailsModel);
        return getUserDetailsModelById(userDetailsModel.getId());
    }

    public Boolean deleteUserDetailsModel(UserDetailsModel userDetailsModel) {
        return mongoTemplate.remove(userDetailsModel).getDeletedCount() > 0 ? true : false;
    }

}
