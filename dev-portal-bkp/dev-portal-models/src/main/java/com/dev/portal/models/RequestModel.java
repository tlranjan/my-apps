package com.dev.portal.models;

import org.springframework.data.mongodb.core.index.Indexed;

public class RequestModel extends DevPortalModel {

    @Indexed(unique = true)
    private String requestName;
    private String requestDescription;
    private String requestedBy;
    private String catalogName;
    private String platformType;
    private String moreInfo;
    private String requestStatus;
    private String requestError;
    private String submittedTime;
    private String lastUpdatedTime;
    private String platformRequestId;
    private String endpointName;

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

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestError() {
        return requestError;
    }

    public void setRequestError(String requestError) {
        this.requestError = requestError;
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

    public String getPlatformRequestId() {
        return platformRequestId;
    }

    public void setPlatformRequestId(String platformRequestId) {
        this.platformRequestId = platformRequestId;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    @Override
    public String toString() {
        return "RequestModel [requestName=" + requestName + ", requestDescription=" + requestDescription
                + ", requestedBy=" + requestedBy + ", catalogName=" + catalogName + ", platformType=" + platformType
                + ", moreInfo=" + moreInfo + ", requestStatus=" + requestStatus + ", requestError=" + requestError
                + ", submittedTime=" + submittedTime + ", lastUpdatedTime=" + lastUpdatedTime + ", platformRequestId="
                + platformRequestId + ", endpointName=" + endpointName + ", id=" + id + "]";
    }

}
