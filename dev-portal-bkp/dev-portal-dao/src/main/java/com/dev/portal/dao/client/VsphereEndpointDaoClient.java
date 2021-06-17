package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dev.portal.models.VsphereEndpointModel;

public class VsphereEndpointDaoClient {

    private MongoTemplate mongoTemplate;

    public VsphereEndpointDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<VsphereEndpointModel> getAllVsphereEndpointModel() {
        return mongoTemplate.findAll(VsphereEndpointModel.class);
    }

    public VsphereEndpointModel getVsphereEndpointModelById(String id) {
        return new DevPortalClientCommon().getModelById(mongoTemplate, VsphereEndpointModel.class, id);
    }

    public VsphereEndpointModel getVsphereEndpointModelByName(String endpointName) {
        return mongoTemplate
                .find(new Query(Criteria.where("endpointName").is(endpointName)), VsphereEndpointModel.class).get(0);
    }

    public VsphereEndpointModel addOrUpdateVsphereEndpointModel(VsphereEndpointModel vsphereEndpointModel) {
        mongoTemplate.save(vsphereEndpointModel);
        return getVsphereEndpointModelByName(vsphereEndpointModel.getEndpointName());
    }

    public Boolean deleteVsphereEndpointModel(String id) {
        return new DevPortalClientCommon().deleteModelById(mongoTemplate, VsphereEndpointModel.class, id);
    }

}
