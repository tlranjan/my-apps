package com.dev.portal.models;

import org.springframework.data.mongodb.core.index.Indexed;

public class OrganizationDetailsModel extends DevPortalModel {

    @Indexed(unique = true)
    private String organizationName;
    private String organizationOwner;

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationOwner() {
        return organizationOwner;
    }

    public void setOrganizationOwner(String organizationOwner) {
        this.organizationOwner = organizationOwner;
    }

    @Override
    public String toString() {
        return "OrganizationDetailsModel [id=" + id + ", organizationName=" + organizationName + ", organizationOwner="
                + organizationOwner + "]";
    }

}
