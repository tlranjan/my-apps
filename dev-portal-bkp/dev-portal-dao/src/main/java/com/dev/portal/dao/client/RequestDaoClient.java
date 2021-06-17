package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dev.portal.models.RequestModel;

public class RequestDaoClient {

    private MongoTemplate mongoTemplate;

    public RequestDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<RequestModel> getAllRequestModel() {
        return mongoTemplate.findAll(RequestModel.class);
    }

    public RequestModel getRequestModelById(String id) {
        return new DevPortalClientCommon().getModelById(mongoTemplate, RequestModel.class, id);
    }

    public List<RequestModel> getRequestModelByName(String requestName) {
        return mongoTemplate.find(new Query(Criteria.where("requestName").is(requestName)), RequestModel.class);
    }
    
    public List<RequestModel> getRequestModelByPlatformType(String platformType) {
        return mongoTemplate.find(new Query(Criteria.where("platformType").is(platformType)), RequestModel.class);
    }

    public RequestModel addOrUpdateRequestModel(RequestModel requestModel) {
        mongoTemplate.save(requestModel);
        return getRequestModelById(requestModel.getId());
    }

    public Boolean deleteRequestModel(String id) {
        return new DevPortalClientCommon().deleteModelById(mongoTemplate, RequestModel.class, id);
    }

}
