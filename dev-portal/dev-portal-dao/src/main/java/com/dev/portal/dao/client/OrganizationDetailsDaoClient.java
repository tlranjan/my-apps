package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.dev.portal.models.OrganizationDetailsModel;

public class OrganizationDetailsDaoClient {

    private MongoTemplate mongoTemplate;
    private DevPortalClientCommon devPortalClientCommon;

    public OrganizationDetailsDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.devPortalClientCommon = new DevPortalClientCommon();
    }

    public List<OrganizationDetailsModel> getOrganizationDetailsModel() {
        return devPortalClientCommon.getModels(mongoTemplate, OrganizationDetailsModel.class);
    }
    
    public List<OrganizationDetailsModel> getOrganizationDetailsModelByOwner(String owner) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, OrganizationDetailsModel.class, "owner", owner);
    }

    public OrganizationDetailsModel getOrganizationDetailsModelById(String id) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, OrganizationDetailsModel.class, "id", id).get(0);
    }

    public OrganizationDetailsModel getOrganizationDetailsModelByName(String name) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, OrganizationDetailsModel.class, "name", name).get(0);
    }

    public OrganizationDetailsModel addOrUpdateOrganizationDetailsModel(OrganizationDetailsModel organizationDetailsModel) {
        updateOtherReferenceModel(organizationDetailsModel);
        mongoTemplate.save(organizationDetailsModel);
        return getOrganizationDetailsModelByName(organizationDetailsModel.getName());
    }

    public Boolean deleteOrganizationDetailsModel(OrganizationDetailsModel organizationDetailsModel) {
        return devPortalClientCommon.deleteModel(mongoTemplate, OrganizationDetailsModel.class, organizationDetailsModel);
    }

    public Boolean deleteOrganizationDetailsModelById(String id) {
        return devPortalClientCommon.deleteModelById(mongoTemplate, OrganizationDetailsModel.class, id);
    }

    public Boolean deleteOrganizationDetailsModelByName(String name) {
        return devPortalClientCommon.deleteModelByName(mongoTemplate, OrganizationDetailsModel.class, name);
    }
    
    private Boolean updateOtherReferenceModel(OrganizationDetailsModel organizationDetailsModel){
        try{
            OrganizationDetailsModel organizationDetailsModelDb = getOrganizationDetailsModelById(organizationDetailsModel.getId());
            if(!(organizationDetailsModelDb.getName().equals(organizationDetailsModel.getName()))){
                updateOrganizationReference(organizationDetailsModelDb.getName(), organizationDetailsModel.getName());
            }
        } catch(Exception e) {
        }
        return true;
    }
    
    private Boolean updateOrganizationReference(String oldOrganization, String newOrganization){
        new AwsCatalogDaoClient(mongoTemplate).updateAwsCatalogModelOrganization(oldOrganization, newOrganization);
        new AwsEndpointDaoClient(mongoTemplate).updateAwsEndpointModelOrganization(oldOrganization, newOrganization);
        new MachineDaoClient(mongoTemplate).updateMachineModelOrganization(oldOrganization, newOrganization);
        new RequestDaoClient(mongoTemplate).updateRequestModelOrganization(oldOrganization, newOrganization);
        new UserDetailsDaoClient(mongoTemplate).updateUserDetailsModelOrganization(oldOrganization, newOrganization);
        new VsphereCatalogDaoClient(mongoTemplate).updateVsphereCatalogModelOrganization(oldOrganization, newOrganization);
        new VsphereEndpointDaoClient(mongoTemplate).updateVsphereEndpointModelOrganization(oldOrganization, newOrganization);
        return true;
    }

}
