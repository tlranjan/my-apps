package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.dev.portal.models.AwsEndpointModel;

public class AwsEndpointDaoClient {

    private MongoTemplate mongoTemplate;
    private DevPortalClientCommon devPortalClientCommon;

    public AwsEndpointDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.devPortalClientCommon = new DevPortalClientCommon();
    }

    public List<AwsEndpointModel> getAwsEndpointModel() {
        return devPortalClientCommon.getModels(mongoTemplate, AwsEndpointModel.class);
    }

    public List<AwsEndpointModel> getAwsEndpointModelByOrganization(String organization) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, AwsEndpointModel.class, "organization", organization);
    }

    public AwsEndpointModel getAwsEndpointModelById(String id) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, AwsEndpointModel.class, "id", id).get(0);
    }

    public AwsEndpointModel getAwsEndpointModelByName(String name) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, AwsEndpointModel.class, "name", name).get(0);
    }

    public AwsEndpointModel addOrUpdateAwsEndpointModel(AwsEndpointModel awsEndpointModel) {
        updateOtherReferenceModel(awsEndpointModel);
        mongoTemplate.save(awsEndpointModel);
        return getAwsEndpointModelByName(awsEndpointModel.getName());
    }

    public Boolean updateAwsEndpointModelOrganization(String oldOrganization, String newOrganization) {
        List<AwsEndpointModel> awsEndpointModelList = getAwsEndpointModelByOrganization(oldOrganization);
        for (AwsEndpointModel awsEndpointModel : awsEndpointModelList) {
            awsEndpointModel.setOrganization(newOrganization);
            mongoTemplate.save(awsEndpointModel);
        }
        return true;
    }

    public Boolean deleteAwsEndpointModel(AwsEndpointModel awsEndpointModel) {
        return devPortalClientCommon.deleteModel(mongoTemplate, AwsEndpointModel.class, awsEndpointModel);
    }

    public Boolean deleteAwsEndpointModelById(String id) {
        return devPortalClientCommon.deleteModelById(mongoTemplate, AwsEndpointModel.class, id);
    }

    public Boolean deleteAwsEndpointModelByName(String name) {
        return devPortalClientCommon.deleteModelByName(mongoTemplate, AwsEndpointModel.class, name);
    }
    
    private Boolean updateOtherReferenceModel(AwsEndpointModel awsEndpointModel){
        try{
            AwsEndpointModel awsEndpointModelDb = getAwsEndpointModelById(awsEndpointModel.getId());
            if(!(awsEndpointModelDb.getName().equals(awsEndpointModel.getName()))){
                updateEndpointReference(awsEndpointModelDb.getName(), awsEndpointModel.getName());
            }
        } catch(Exception e) {
        }
        return true;
    }

    private Boolean updateEndpointReference(String oldEndpoint, String newEndpoint){
        new MachineDaoClient(mongoTemplate).updateMachineModelEndpoint(oldEndpoint, newEndpoint);
        new RequestDaoClient(mongoTemplate).updateRequestModelEndpoint(oldEndpoint, newEndpoint);
        new AwsCatalogDaoClient(mongoTemplate).updateAwsCatalogModelEndpoint(oldEndpoint, newEndpoint);
        return true;
    }

}
