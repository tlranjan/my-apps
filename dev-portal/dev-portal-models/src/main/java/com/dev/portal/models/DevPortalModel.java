package com.dev.portal.models;

import org.springframework.data.mongodb.core.index.Indexed;

public class DevPortalModel {

    private String id;
    @Indexed(unique = true)
    protected String name;
    protected String description;
    protected String organization;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "DevPortalModel [id=" + id + ", name=" + name + ", description=" + description + ", organization="
                + organization + "]";
    }

}
