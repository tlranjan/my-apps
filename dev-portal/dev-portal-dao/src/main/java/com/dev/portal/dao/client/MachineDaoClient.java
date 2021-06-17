package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.dev.portal.models.MachineModel;

public class MachineDaoClient {

    private MongoTemplate mongoTemplate;
    private DevPortalClientCommon devPortalClientCommon;

    public MachineDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.devPortalClientCommon = new DevPortalClientCommon();
    }

    public List<MachineModel> getMachineModel() {
        return devPortalClientCommon.getModels(mongoTemplate, MachineModel.class);
    }

    public List<MachineModel> getMachineModelByOrganization(String organization) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, MachineModel.class, "organization", organization);
    }
    
    public List<MachineModel> getMachineModelByEndpoint(String endpoint) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, MachineModel.class, "endpoint", endpoint);
    }
    
    public List<MachineModel> getMachineModelByOwner(String owner) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, MachineModel.class, "owner", owner);
    }

    public MachineModel getMachineModelById(String id) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, MachineModel.class, "id", id).get(0);
    }

    public MachineModel getMachineModelByName(String name) {
        return devPortalClientCommon.getModelsByKeyValue(mongoTemplate, MachineModel.class, "name", name).get(0);
    }

    public MachineModel addOrUpdateMachineModel(MachineModel machineModel) {
        mongoTemplate.save(machineModel);
        return getMachineModelByName(machineModel.getName());
    }

    public Boolean updateMachineModelOrganization(String oldOrganization, String newOrganization) {
        List<MachineModel> machineModelList = getMachineModelByOrganization(oldOrganization);
        for (MachineModel machineModel : machineModelList) {
            machineModel.setOrganization(newOrganization);
            mongoTemplate.save(machineModel);
        }
        return true;
    }
    
    public Boolean updateMachineModelEndpoint(String oldEndpoint, String newEndpoint) {
        List<MachineModel> machineModelList = getMachineModelByEndpoint(oldEndpoint);
        for (MachineModel machineModel : machineModelList) {
            machineModel.setEndpoint(newEndpoint);
            mongoTemplate.save(machineModel);
        }
        return true;
    }

    public Boolean deleteMachineModel(MachineModel machineModel) {
        return devPortalClientCommon.deleteModel(mongoTemplate, MachineModel.class, machineModel);
    }

    public Boolean deleteMachineModelById(String id) {
        return devPortalClientCommon.deleteModelById(mongoTemplate, MachineModel.class, id);
    }

    public Boolean deleteMachineModelByName(String name) {
        return devPortalClientCommon.deleteModelByName(mongoTemplate, MachineModel.class, name);
    }

}
