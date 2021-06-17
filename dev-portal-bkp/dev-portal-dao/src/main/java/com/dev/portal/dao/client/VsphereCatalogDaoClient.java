package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dev.portal.models.VsphereCatalogModel;

public class VsphereCatalogDaoClient {

    private MongoTemplate mongoTemplate;

    public VsphereCatalogDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<VsphereCatalogModel> getAllVsphereCatalogModel() {
        return mongoTemplate.findAll(VsphereCatalogModel.class);
    }

    public VsphereCatalogModel getVsphereCatalogModelById(String id) {
        return new DevPortalClientCommon().getModelById(mongoTemplate, VsphereCatalogModel.class, id);
    }

    public VsphereCatalogModel getVsphereCatalogModelByName(String catalogName) {
        return mongoTemplate.find(new Query(Criteria.where("catalogName").is(catalogName)), VsphereCatalogModel.class)
                .get(0);
    }

    public VsphereCatalogModel addOrUpdateVsphereCatalogModel(VsphereCatalogModel vsphereCatalogModel) {
        mongoTemplate.save(vsphereCatalogModel);
        return getVsphereCatalogModelByName(vsphereCatalogModel.getCatalogName());
    }

    public Boolean deleteVsphereCatalogModel(String id) {
        return new DevPortalClientCommon().deleteModelById(mongoTemplate, VsphereCatalogModel.class, id);
    }

}
