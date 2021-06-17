package com.dev.portal.models;

public class MachineModel extends DevPortalModel {

    private String platformType;
    private String powerState;
    private String machineIP;
    private String organizationName;
    private String machineOwner;
    private String endpointName;
    private String catalogName;
    private String cpuCount;
    private String memoryMB;
    private String storageGB;
    private String moreInfo;

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getPowerState() {
        return powerState;
    }

    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }

    public String getMachineIP() {
        return machineIP;
    }

    public void setMachineIP(String machineIP) {
        this.machineIP = machineIP;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getMachineOwner() {
        return machineOwner;
    }

    public void setMachineOwner(String machineOwner) {
        this.machineOwner = machineOwner;
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

    public String getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(String cpuCount) {
        this.cpuCount = cpuCount;
    }

    public String getMemoryMB() {
        return memoryMB;
    }

    public void setMemoryMB(String memoryMB) {
        this.memoryMB = memoryMB;
    }

    public String getStorageGB() {
        return storageGB;
    }

    public void setStorageGB(String storageGB) {
        this.storageGB = storageGB;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return "MachineModel [platformType=" + platformType + ", powerState=" + powerState + ", machineIP=" + machineIP
                + ", organizationName=" + organizationName + ", machineOwner=" + machineOwner + ", endpointName="
                + endpointName + ", catalogName=" + catalogName + ", cpuCount=" + cpuCount + ", memoryMB=" + memoryMB
                + ", storageGB=" + storageGB + ", moreInfo=" + moreInfo + ", id=" + id + "]";
    }

}
