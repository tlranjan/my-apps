package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dev.portal.models.VsphereCatalogModel;
import com.dev.portal.models.contants.Published;

public class VsphereCatalogDaoClient {

    private MongoTemplate mongoTemplate;
    private DevPortalClientCommon devPortalClientCommon;

    public VsphereCatalogDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.devPortalClientCommon = new DevPortalClientCommon();
    }

    public List<VsphereCatalogModel> getVsphereCatalogModel() {
        return devPortalClientCommon.getModels(mongoTemplate, VsphereCatalogModel.class);
    }

    public List<VsphereCatalogModel> getVsphereCatalogModelByOrganization(String organization) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, VsphereCatalogModel.class, "organization", organization);
    }
    
    public List<VsphereCatalogModel> getVsphereCatalogModelByEndpoint(String endpoint) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, VsphereCatalogModel.class, "endpoint", endpoint);
    }
    
    public List<VsphereCatalogModel> getVsphereCatalogModelByOwner(String owner) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, VsphereCatalogModel.class, "owner", owner);
    }
    
    public List<VsphereCatalogModel> getVsphereCatalogModelByPublished(String organization) {
        return mongoTemplate.find(new Query(Criteria.where("organization").is(organization).andOperator(Criteria.where("published").is(Published.YES.toString()))), VsphereCatalogModel.class);
    }

    public VsphereCatalogModel getVsphereCatalogModelById(String id) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, VsphereCatalogModel.class, "id", id).get(0);
    }

    public VsphereCatalogModel getVsphereCatalogModelByName(String name) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, VsphereCatalogModel.class, "name", name).get(0);
    }

    public VsphereCatalogModel addOrUpdateVsphereCatalogModel(VsphereCatalogModel vsphereCatalogModel) {
        mongoTemplate.save(vsphereCatalogModel);
        return getVsphereCatalogModelByName(vsphereCatalogModel.getName());
    }

    public Boolean updateVsphereCatalogModelOrganization(String oldOrganization, String newOrganization) {
        List<VsphereCatalogModel> vsphereCatalogModelList = getVsphereCatalogModelByOrganization(oldOrganization);
        for (VsphereCatalogModel vsphereCatalogModel : vsphereCatalogModelList) {
            vsphereCatalogModel.setOrganization(newOrganization);
            mongoTemplate.save(vsphereCatalogModel);
        }
        return true;
    }
    
    public Boolean updateVsphereCatalogModelEndpoint(String oldEndpoint, String newEndpoint) {
        List<VsphereCatalogModel> vsphereCatalogModelList = getVsphereCatalogModelByEndpoint(oldEndpoint);
        for (VsphereCatalogModel vsphereCatalogModel : vsphereCatalogModelList) {
            vsphereCatalogModel.setEndpoint(newEndpoint);
            mongoTemplate.save(vsphereCatalogModel);
        }
        return true;
    }

    public Boolean deleteVsphereCatalogModel(VsphereCatalogModel vsphereCatalogModel) {
        return devPortalClientCommon.deleteModel(mongoTemplate, VsphereCatalogModel.class, vsphereCatalogModel);
    }

    public Boolean deleteVsphereCatalogModelById(String id) {
        return devPortalClientCommon.deleteModelById(mongoTemplate, VsphereCatalogModel.class, id);
    }

    public Boolean deleteVsphereCatalogModelByName(String name) {
        return devPortalClientCommon.deleteModelByName(mongoTemplate, VsphereCatalogModel.class, name);
    }

}
