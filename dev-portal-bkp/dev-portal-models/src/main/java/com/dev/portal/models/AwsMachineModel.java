package com.dev.portal.models;

import org.springframework.data.mongodb.core.index.Indexed;

public class AwsMachineModel extends DevPortalModel {

    @Indexed(unique = true)
    private String machineName;
    private String machineIP;
    private String machineDnsName;
    private String machineDescription;
    private String machineOwner;
    private String organizationName;
    private String endpointName;
    private String region;
    private String imageId;
    private String instanceType;
    private String storageGB;
    private String keyPairName;
    private String securityGroupName;
    private String moreDetails;

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineIP() {
        return machineIP;
    }

    public void setMachineIP(String machineIP) {
        this.machineIP = machineIP;
    }

    public String getMachineDnsName() {
        return machineDnsName;
    }

    public void setMachineDnsName(String machineDnsName) {
        this.machineDnsName = machineDnsName;
    }

    public String getMachineDescription() {
        return machineDescription;
    }

    public void setMachineDescription(String machineDescription) {
        this.machineDescription = machineDescription;
    }

    public String getMachineOwner() {
        return machineOwner;
    }

    public void setMachineOwner(String machineOwner) {
        this.machineOwner = machineOwner;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getStorageGB() {
        return storageGB;
    }

    public void setStorageGB(String storageGB) {
        this.storageGB = storageGB;
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

    public String getMoreDetails() {
        return moreDetails;
    }

    public void setMoreDetails(String moreDetails) {
        this.moreDetails = moreDetails;
    }

    @Override
    public String toString() {
        return "AwsMachineModel [machineName=" + machineName + ", machineIP=" + machineIP + ", machineDnsName="
                + machineDnsName + ", machineDescription=" + machineDescription + ", machineOwner=" + machineOwner
                + ", organizationName=" + organizationName + ", endpointName=" + endpointName + ", region=" + region
                + ", imageId=" + imageId + ", instanceType=" + instanceType + ", storageGB=" + storageGB
                + ", keyPairName=" + keyPairName + ", securityGroupName=" + securityGroupName + ", moreDetails="
                + moreDetails + ", id=" + id + "]";
    }

}
