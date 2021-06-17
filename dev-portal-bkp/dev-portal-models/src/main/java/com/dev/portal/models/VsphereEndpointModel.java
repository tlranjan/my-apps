package com.dev.portal.models;

import org.springframework.data.mongodb.core.index.Indexed;

public class VsphereEndpointModel extends DevPortalModel {

    @Indexed(unique = true)
    private String endpointName;
    private String vcSdkUrl;
    private String vcenterUser;
    private String vcenterPassword;
    private String organizationName;
    private String vsphereComputes;

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getVcSdkUrl() {
        return vcSdkUrl;
    }

    public void setVcSdkUrl(String vcSdkUrl) {
        this.vcSdkUrl = vcSdkUrl;
    }

    public String getVcenterUser() {
        return vcenterUser;
    }

    public void setVcenterUser(String vcenterUser) {
        this.vcenterUser = vcenterUser;
    }

    public String getVcenterPassword() {
        return vcenterPassword;
    }

    public void setVcenterPassword(String vcenterPassword) {
        this.vcenterPassword = vcenterPassword;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getVsphereComputes() {
        return vsphereComputes;
    }

    public void setVsphereComputes(String vsphereComputes) {
        this.vsphereComputes = vsphereComputes;
    }

    @Override
    public String toString() {
        return "VsphereEndpointModel [endpointName=" + endpointName + ", vcSdkUrl=" + vcSdkUrl + ", vcenterUser="
                + vcenterUser + ", vcenterPassword=" + vcenterPassword + ", organizationName=" + organizationName
                + ", vsphereComputes=" + vsphereComputes + ", id=" + id + "]";
    }

}
