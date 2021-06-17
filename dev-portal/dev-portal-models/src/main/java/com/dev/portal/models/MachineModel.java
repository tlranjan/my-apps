package com.dev.portal.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MachineModel")
public class MachineModel extends DevPortalModel {

    private String ip;
    private String state;
    private String owner;
    private String catalog;
    private String endpoint;
    private String platform;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "MachineModel [ip=" + ip + ", state=" + state + ", owner=" + owner + ", catalog=" + catalog
                + ", endpoint=" + endpoint + ", platform=" + platform + ", name=" + name + ", description="
                + description + ", organization=" + organization + "]";
    }

}
