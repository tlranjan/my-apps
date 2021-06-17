package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dev.portal.models.AwsCatalogModel;

public class AwsCatalogDaoClient {

    private MongoTemplate mongoTemplate;

    public AwsCatalogDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<AwsCatalogModel> getAllAwsCatalogModel() {
        return mongoTemplate.findAll(AwsCatalogModel.class);
    }

    public AwsCatalogModel getAwsCatalogModelById(String id) {
        return new DevPortalClientCommon().getModelById(mongoTemplate, AwsCatalogModel.class, id);
    }

    public AwsCatalogModel getAwsCatalogModelByName(String catalogName) {
        return mongoTemplate.find(new Query(Criteria.where("catalogName").is(catalogName)), AwsCatalogModel.class)
                .get(0);
    }

    public AwsCatalogModel addOrUpdateAwsCatalogModel(AwsCatalogModel awsCatalogModel) {
        mongoTemplate.save(awsCatalogModel);
        return getAwsCatalogModelByName(awsCatalogModel.getCatalogName());
    }

    public Boolean deleteAwsCatalogModel(String id) {
        return new DevPortalClientCommon().deleteModelById(mongoTemplate, AwsCatalogModel.class, id);
    }

}
