package com.dev.portal.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RequestModel")
public class RequestModel extends DevPortalModel {

    private String catalog;
    private String platform;
    private String endpoint;
    private String owner;
    private String status;
    private String submittedTime;
    private String lastUpdatedTime;
    private String error;

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(String submittedTime) {
        this.submittedTime = submittedTime;
    }

    public String getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(String lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "RequestModel [catalog=" + catalog + ", platform=" + platform + ", endpoint=" + endpoint + ", owner="
                + owner + ", status=" + status + ", submittedTime=" + submittedTime + ", lastUpdatedTime="
                + lastUpdatedTime + ", error=" + error + ", name=" + name + ", description=" + description
                + ", organization=" + organization + "]";
    }

}
