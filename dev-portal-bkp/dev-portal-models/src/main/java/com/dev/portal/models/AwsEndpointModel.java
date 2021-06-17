package com.dev.portal.models;

import org.springframework.data.mongodb.core.index.Indexed;

public class AwsEndpointModel extends DevPortalModel {

    @Indexed(unique = true)
    private String endpointName;
    private String accessKey;
    private String secretKey;
    private String region;
    private String organizationName;

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public String toString() {
        return "AwsEndpointModel [endpointName=" + endpointName + ", accessKey=" + accessKey + ", secretKey="
                + secretKey + ", region=" + region + ", organizationName=" + organizationName + ", id=" + id + "]";
    }

}
