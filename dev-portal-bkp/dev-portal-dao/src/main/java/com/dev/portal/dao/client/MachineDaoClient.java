package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.dev.portal.models.MachineModel;

public class MachineDaoClient {

    private MongoTemplate mongoTemplate;

    public MachineDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<MachineModel> getAllMachineModel() {
        return mongoTemplate.findAll(MachineModel.class);
    }

    public MachineModel getMachineModelById(String id) {
        return new DevPortalClientCommon().getModelById(mongoTemplate, MachineModel.class, id);
    }

    public MachineModel addOrUpdateMachineModel(MachineModel MachineModel) {
        mongoTemplate.save(MachineModel);
        return getMachineModelById(MachineModel.getId());
    }

    public Boolean deleteMachineModel(String id) {
        return new DevPortalClientCommon().deleteModelById(mongoTemplate, MachineModel.class, id);
    }

}
