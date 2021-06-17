package com.dev.portal.dao.client;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dev.portal.models.OrganizationDetailsModel;

public class OrganizationDetailsDaoClient {

    private MongoTemplate mongoTemplate;

    public OrganizationDetailsDaoClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<OrganizationDetailsModel> getAllOrganizationDetailsModel() {
        return mongoTemplate.findAll(OrganizationDetailsModel.class);
    }
    
    public List<OrganizationDetailsModel> getAllOrganizationDetailsModel(String organizationOwner) {
        return mongoTemplate.find(new Query(Criteria.where("organizationOwner").is(organizationOwner)), OrganizationDetailsModel.class);
    }

    public OrganizationDetailsModel getOrganizationDetailsModel(String id) {
        return new DevPortalClientCommon().getModelById(mongoTemplate, OrganizationDetailsModel.class, id);
    }

    public OrganizationDetailsModel addOrganizationDetailsModel(OrganizationDetailsModel organizationDetailsModel) {
        mongoTemplate.save(organizationDetailsModel);
        return getOrganizationDetailsModel(organizationDetailsModel.getId());
    }

    public OrganizationDetailsModel updateOrganizationDetailsModel(OrganizationDetailsModel organizationDetailsModel) {
        //set this org users to organizationName/organizationOwner org
        //set this org vms to organizationName/organizationOwner org
        mongoTemplate.save(organizationDetailsModel);
        return getOrganizationDetailsModel(organizationDetailsModel.getId());
    }

    public Boolean deleteOrganizationDetailsModel(OrganizationDetailsModel organizationDetailsModel) {
        //set this org users to default/owner org
        //set this org vms to default/owner org
        return mongoTemplate.remove(organizationDetailsModel).getDeletedCount() > 0 ? true : false;
    }
    
    public Boolean deleteOrganizationDetailsModel(String id) {
        //set this org users to default/owner org
        //set this org vms to default/owner org
        return new DevPortalClientCommon().deleteModelById(mongoTemplate, OrganizationDetailsModel.class, id);
    }

}
