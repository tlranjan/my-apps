package com.dev.portal.models;

public class CatalogRequestModel extends DevPortalModel {

    private String requestName;
    private String requestDescription;
    private String requestCount;
    private String endpointName;
    private String catalogName;

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(String requestCount) {
        this.requestCount = requestCount;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    @Override
    public String toString() {
        return "CatalogRequestModel [requestName=" + requestName + ", requestDescription=" + requestDescription
                + ", requestCount=" + requestCount + ", endpointName=" + endpointName + ", catalogName=" + catalogName
                + ", id=" + id + "]";
    }

}
