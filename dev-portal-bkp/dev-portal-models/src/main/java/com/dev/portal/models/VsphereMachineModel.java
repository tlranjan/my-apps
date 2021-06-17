package com.dev.portal.models;

import org.springframework.data.mongodb.core.index.Indexed;

public class VsphereMachineModel extends DevPortalModel {

    @Indexed(unique = true)
    private String machineName;
    private String machineIP;
    private String machineDnsName;
    private String machineDescription;
    private String machineOwner;
    private String organizationName;
    private String endpointName;
    private String vmTemplate;
    private String memoryMB;
    private String cpu;
    private String storageGB;
    private String datastore;
    private String network;
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

    public String getMoreDetails() {
        return moreDetails;
    }

    public void setMoreDetails(String moreDetails) {
        this.moreDetails = moreDetails;
    }

    @Override
    public String toString() {
        return "VsphereMachineModel [machineName=" + machineName + ", machineIP=" + machineIP + ", machineDnsName="
                + machineDnsName + ", machineDescription=" + machineDescription + ", machineOwner=" + machineOwner
                + ", organizationName=" + organizationName + ", endpointName=" + endpointName + ", vmTemplate="
                + vmTemplate + ", memoryMB=" + memoryMB + ", cpu=" + cpu + ", storageGB=" + storageGB + ", datastore="
                + datastore + ", network=" + network + ", moreDetails=" + moreDetails + ", id=" + id + "]";
    }

}
