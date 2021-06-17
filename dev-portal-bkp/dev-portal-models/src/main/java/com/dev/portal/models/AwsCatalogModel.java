package com.dev.portal.models;

import org.springframework.data.mongodb.core.index.Indexed;

public class AwsCatalogModel extends DevPortalModel {

    @Indexed(unique = true)
    private String catalogName;
    private String catalogDescription;
    private String catalogOwner;
    private String organizationName;
    private String endpointName;
    private String imageId;
    private String instanceType;
    private String keyPairName;
    private String securityGroupName;
    private String isPublished;

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogDescription() {
        return catalogDescription;
    }

    public void setCatalogDescription(String catalogDescription) {
        this.catalogDescription = catalogDescription;
    }

    public String getCatalogOwner() {
        return catalogOwner;
    }

    public void setCatalogOwner(String catalogOwner) {
        this.catalogOwner = catalogOwner;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getKeyPairName() {
        return keyPairName;
    }

    public void setKeyPairName(String keyPairName) {
        this.keyPairName = keyPairName;
    }

    public String getSecurityGroupName() {
        return securityGroupName;
    }

    public void setSecurityGroupName(String securityGroupName) {
        this.securityGroupName = securityGroupName;
    }

    public String getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(String isPublished) {
        this.isPublished = isPublished;
    }

    @Override
    public String toString() {
        return "AwsCatalogModel [catalogName=" + catalogName + ", catalogDescription=" + catalogDescription
                + ", catalogOwner=" + catalogOwner + ", organizationName=" + organizationName + ", endpointName="
                + endpointName + ", imageId=" + imageId + ", instanceType=" + instanceType + ", keyPairName="
                + keyPairName + ", securityGroupName=" + securityGroupName + ", isPublished=" + isPublished + ", id="
                + id + "]";
    }

}
