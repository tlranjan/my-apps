package com.dev.portal.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "VsphereEndpointModel")
public class VsphereEndpointModel extends DevPortalModel {

    private String sdkUrl;
    private String user;
    private String password;
    private String computes;

    public String getSdkUrl() {
        return sdkUrl;
    }

    public void setSdkUrl(String sdkUrl) {
        this.sdkUrl = sdkUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComputes() {
        return computes;
    }

    public void setComputes(String computes) {
        this.computes = computes;
    }

    @Override
    public String toString() {
        return "VsphereEndpointModel [sdkUrl=" + sdkUrl + ", user=" + user + ", password=" + password + ", computes="
                + computes + ", name=" + name + ", description=" + description + ", organization=" + organization + "]";
    }

}
