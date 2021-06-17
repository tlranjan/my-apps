package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dev.portal.models.RequestModel;

public class RequestDaoClient {

    private MongoTemplate mongoTemplate;
    private DevPortalClientCommon devPortalClientCommon;

    public RequestDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.devPortalClientCommon = new DevPortalClientCommon();
    }

    public List<RequestModel> getRequestModel() {
        return devPortalClientCommon.getModels(mongoTemplate, RequestModel.class);
    }

    public List<RequestModel> getRequestModelByOrganization(String organization) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, RequestModel.class, "organization", organization);
    }
    
    public List<RequestModel> getRequestModelByPlatform(String platform) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, RequestModel.class, "platform", platform);
    }
    
    public List<RequestModel> getRequestModelByPlatformAndOwner(String platform, String owner) {
        Query query = new Query(Criteria.where("platform").is(platform).andOperator(Criteria.where("owner").is(owner)));
        return mongoTemplate.find(query, RequestModel.class);
    }
    
    public List<RequestModel> getRequestModelByPlatformAndOrganization(String platform, String organization) {
        Query query = new Query(Criteria.where("platform").is(platform).andOperator(Criteria.where("organization").is(organization)));
        return mongoTemplate.find(query, RequestModel.class);
    }
    
    public List<RequestModel> getRequestModelByEndpoint(String endpoint) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, RequestModel.class, "endpoint", endpoint);
    }
    
    public List<RequestModel> getRequestModelByOwner(String owner) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, RequestModel.class, "owner", owner);
    }

    public RequestModel getRequestModelById(String id) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, RequestModel.class, "id", id).get(0);
    }

    public RequestModel getRequestModelByName(String name) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, RequestModel.class, "name", name).get(0);
    }

    public RequestModel addOrUpdateRequestModel(RequestModel requestModel) {
        mongoTemplate.save(requestModel);
        return getRequestModelByName(requestModel.getName());
    }

    public Boolean updateRequestModelOrganization(String oldOrganization, String newOrganization) {
        List<RequestModel> requestModelList = getRequestModelByOrganization(oldOrganization);
        for (RequestModel requestModel : requestModelList) {
            requestModel.setOrganization(newOrganization);
            mongoTemplate.save(requestModel);
        }
        return true;
    }
    
    public Boolean updateRequestModelEndpoint(String oldEndpoint, String newEndpoint) {
        List<RequestModel> requestModelList = getRequestModelByEndpoint(oldEndpoint);
        for (RequestModel requestModel : requestModelList) {
            requestModel.setEndpoint(newEndpoint);
            mongoTemplate.save(requestModel);
        }
        return true;
    }

    public Boolean deleteRequestModel(RequestModel requestModel) {
        return devPortalClientCommon.deleteModel(mongoTemplate, RequestModel.class, requestModel);
    }

    public Boolean deleteRequestModelById(String id) {
        return devPortalClientCommon.deleteModelById(mongoTemplate, RequestModel.class, id);
    }

    public Boolean deleteRequestModelByName(String name) {
        return devPortalClientCommon.deleteModelByName(mongoTemplate, RequestModel.class, name);
    }

}
