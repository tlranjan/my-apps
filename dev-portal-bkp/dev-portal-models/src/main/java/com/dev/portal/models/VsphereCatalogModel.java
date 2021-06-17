package com.dev.portal.models;

import org.springframework.data.mongodb.core.index.Indexed;

public class VsphereCatalogModel extends DevPortalModel {

    @Indexed(unique = true)
    private String catalogName;
    private String catalogDescription;
    private String catalogOwner;
    private String organizationName;
    private String endpointName;
    private String vmTemplate;
    private String memoryMB;
    private String cpu;
    private String storageGB;
    private String datastore;
    private String network;
    private Boolean isPublished;

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

    public String getVmTemplate() {
        return vmTemplate;
    }

    public void setVmTemplate(String vmTemplate) {
        this.vmTemplate = vmTemplate;
    }

    public String getMemoryMB() {
        return memoryMB;
    }

    public void setMemoryMB(String memoryMB) {
        this.memoryMB = memoryMB;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getStorageGB() {
        return storageGB;
    }

    public void setStorageGB(String storageGB) {
        this.storageGB = storageGB;
    }

    public String getDatastore() {
        return datastore;
    }

    public void setDatastore(String datastore) {
        this.datastore = datastore;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    @Override
    public String toString() {
        return "VsphereCatalogModel [catalogName=" + catalogName + ", catalogDescription=" + catalogDescription
                + ", catalogOwner=" + catalogOwner + ", organizationName=" + organizationName + ", endpointName="
                + endpointName + ", vmTemplate=" + vmTemplate + ", memoryMB=" + memoryMB + ", cpu=" + cpu
                + ", storageGB=" + storageGB + ", datastore=" + datastore + ", network=" + network + ", isPublished="
                + isPublished + ", id=" + id + "]";
    }

}
