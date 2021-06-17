package com.dev.portal.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OrganizationDetailsModel")
public class OrganizationDetailsModel extends DevPortalModel {

    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "OrganizationDetailsModel [owner=" + owner + ", name=" + name + ", description=" + description
                + ", organization=" + organization + "]";
    }

}
