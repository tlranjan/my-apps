package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dev.portal.models.AwsCatalogModel;
import com.dev.portal.models.contants.Published;

public class AwsCatalogDaoClient {

    private MongoTemplate mongoTemplate;
    private DevPortalClientCommon devPortalClientCommon;

    public AwsCatalogDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.devPortalClientCommon = new DevPortalClientCommon();
    }

    public List<AwsCatalogModel> getAwsCatalogModel() {
        return devPortalClientCommon.getModels(mongoTemplate, AwsCatalogModel.class);
    }

    public List<AwsCatalogModel> getAwsCatalogModelByOrganization(String organization) {
        System.out.println("6====");
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, AwsCatalogModel.class, "organization", organization);
    }
    
    public List<AwsCatalogModel> getAwsCatalogModelByEndpoint(String endpoint) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, AwsCatalogModel.class, "endpoint", endpoint);
    }
    
    public List<AwsCatalogModel> getAwsCatalogModelByOwner(String owner) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, AwsCatalogModel.class, "owner", owner);
    }
    
    public List<AwsCatalogModel> getAwsCatalogModelByPublished(String organization) {
        return mongoTemplate.find(new Query(Criteria.where("organization").is(organization).andOperator(Criteria.where("published").is(Published.YES.toString()))), AwsCatalogModel.class);
    }

    public AwsCatalogModel getAwsCatalogModelById(String id) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, AwsCatalogModel.class, "id", id).get(0);
    }

    public AwsCatalogModel getAwsCatalogModelByName(String name) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, AwsCatalogModel.class, "name", name).get(0);
    }

    public AwsCatalogModel addOrUpdateAwsCatalogModel(AwsCatalogModel awsCatalogModel) {
        mongoTemplate.save(awsCatalogModel);
        return getAwsCatalogModelByName(awsCatalogModel.getName());
    }

    public Boolean updateAwsCatalogModelOrganization(String oldOrganization, String newOrganization) {
        List<AwsCatalogModel> awsCatalogModelList = getAwsCatalogModelByOrganization(oldOrganization);
        for (AwsCatalogModel awsCatalogModel : awsCatalogModelList) {
            awsCatalogModel.setOrganization(newOrganization);
            mongoTemplate.save(awsCatalogModel);
        }
        return true;
    }
    
    public Boolean updateAwsCatalogModelEndpoint(String oldEndpoint, String newEndpoint) {
        List<AwsCatalogModel> awsCatalogModelList = getAwsCatalogModelByEndpoint(oldEndpoint);
        for (AwsCatalogModel awsCatalogModel : awsCatalogModelList) {
            awsCatalogModel.setEndpoint(newEndpoint);
            mongoTemplate.save(awsCatalogModel);
        }
        return true;
    }

    public Boolean deleteAwsCatalogModel(AwsCatalogModel awsCatalogModel) {
        return devPortalClientCommon.deleteModel(mongoTemplate, AwsCatalogModel.class, awsCatalogModel);
    }

    public Boolean deleteAwsCatalogModelById(String id) {
        return devPortalClientCommon.deleteModelById(mongoTemplate, AwsCatalogModel.class, id);
    }

    public Boolean deleteAwsCatalogModelByName(String name) {
        return devPortalClientCommon.deleteModelByName(mongoTemplate, AwsCatalogModel.class, name);
    }

}
