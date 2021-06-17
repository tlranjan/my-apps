package com.dev.portal.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AwsEndpointModel")
public class AwsEndpointModel extends DevPortalModel {

    private String accessKey;
    private String secretKey;
    private String region;

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

    @Override
    public String toString() {
        return "AwsEndpointModel [accessKey=" + accessKey + ", secretKey=" + secretKey + ", region=" + region
                + ", name=" + name + ", description=" + description + ", organization=" + organization + "]";
    }

}
