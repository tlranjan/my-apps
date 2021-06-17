package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.dev.portal.models.VsphereEndpointModel;

public class VsphereEndpointDaoClient {

    private MongoTemplate mongoTemplate;
    private DevPortalClientCommon devPortalClientCommon;

    public VsphereEndpointDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.devPortalClientCommon = new DevPortalClientCommon();
    }

    public List<VsphereEndpointModel> getVsphereEndpointModel() {
        return devPortalClientCommon.getModels(mongoTemplate, VsphereEndpointModel.class);
    }

    public List<VsphereEndpointModel> getVsphereEndpointModelByOrganization(String organization) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, VsphereEndpointModel.class, "organization", organization);
    }

    public VsphereEndpointModel getVsphereEndpointModelById(String id) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, VsphereEndpointModel.class, "id", id).get(0);
    }

    public VsphereEndpointModel getVsphereEndpointModelByName(String name) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, VsphereEndpointModel.class, "name", name).get(0);
    }

    public VsphereEndpointModel addOrUpdateVsphereEndpointModel(VsphereEndpointModel vsphereEndpointModel) {
        updateOtherReferenceModel(vsphereEndpointModel);
        mongoTemplate.save(vsphereEndpointModel);
        return getVsphereEndpointModelByName(vsphereEndpointModel.getName());
    }

    public Boolean updateVsphereEndpointModelOrganization(String oldOrganization, String newOrganization) {
        List<VsphereEndpointModel> vsphereEndpointModelList = getVsphereEndpointModelByOrganization(oldOrganization);
        for (VsphereEndpointModel vsphereEndpointModel : vsphereEndpointModelList) {
            vsphereEndpointModel.setOrganization(newOrganization);
            mongoTemplate.save(vsphereEndpointModel);
        }
        return true;
    }

    public Boolean deleteVsphereEndpointModel(VsphereEndpointModel vsphereEndpointModel) {
        return devPortalClientCommon.deleteModel(mongoTemplate, VsphereEndpointModel.class, vsphereEndpointModel);
    }

    public Boolean deleteVsphereEndpointModelById(String id) {
        return devPortalClientCommon.deleteModelById(mongoTemplate, VsphereEndpointModel.class, id);
    }

    public Boolean deleteVsphereEndpointModelByName(String name) {
        return devPortalClientCommon.deleteModelByName(mongoTemplate, VsphereEndpointModel.class, name);
    }
    
    private Boolean updateOtherReferenceModel(VsphereEndpointModel vsphereEndpointModel){
        try{
            VsphereEndpointModel vsphereEndpointModelDb = getVsphereEndpointModelById(vsphereEndpointModel.getId());
            if(!(vsphereEndpointModelDb.getName().equals(vsphereEndpointModel.getName()))){
                updateEndpointReference(vsphereEndpointModelDb.getName(), vsphereEndpointModel.getName());
            }
        } catch(Exception e) {
        }
        return true;
    }
    
    private Boolean updateEndpointReference(String oldEndpoint, String newEndpoint){
        new MachineDaoClient(mongoTemplate).updateMachineModelEndpoint(oldEndpoint, newEndpoint);
        new RequestDaoClient(mongoTemplate).updateRequestModelEndpoint(oldEndpoint, newEndpoint);
        new VsphereCatalogDaoClient(mongoTemplate).updateVsphereCatalogModelEndpoint(oldEndpoint, newEndpoint);
        return true;
    }

}
