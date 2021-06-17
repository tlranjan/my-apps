package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dev.portal.models.AwsEndpointModel;

public class AwsEndpointDaoClient {

    private MongoTemplate mongoTemplate;

    public AwsEndpointDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<AwsEndpointModel> getAllAwsEndpointModel() {
        return mongoTemplate.findAll(AwsEndpointModel.class);
    }

    public AwsEndpointModel getAwsEndpointModelById(String id) {
        return new DevPortalClientCommon().getModelById(mongoTemplate, AwsEndpointModel.class, id);
    }

    public AwsEndpointModel getAwsEndpointModelByName(String endpointName) {
        return mongoTemplate.find(new Query(Criteria.where("endpointName").is(endpointName)), AwsEndpointModel.class)
                .get(0);
    }

    public AwsEndpointModel addOrUpdateAwsEndpointModel(AwsEndpointModel awsEndpointModel) {
        mongoTemplate.save(awsEndpointModel);
        return getAwsEndpointModelByName(awsEndpointModel.getEndpointName());
    }

    public Boolean deleteAwsEndpointModel(String id) {
        return new DevPortalClientCommon().deleteModelById(mongoTemplate, AwsEndpointModel.class, id);
    }

}
