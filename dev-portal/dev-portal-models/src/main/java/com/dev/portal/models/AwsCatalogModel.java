package com.dev.portal.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AwsCatalogModel")
public class AwsCatalogModel extends DevPortalModel {

    private String amiId;
    private String instanceType;
    private String securityGroup;
    private String endpoint;
    private String owner;
    private String published;

    public String getAmiId() {
        return amiId;
    }

    public void setAmiId(String amiId) {
        this.amiId = amiId;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getSecurityGroup() {
        return securityGroup;
    }

    public void setSecurityGroup(String securityGroup) {
        this.securityGroup = securityGroup;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "AwsCatalogModel [amiId=" + amiId + ", instanceType=" + instanceType + ", securityGroup=" + securityGroup
                + ", endpoint=" + endpoint + ", owner=" + owner + ", published=" + published + ", name=" + name
                + ", description=" + description + ", organization=" + organization + "]";
    }

}
