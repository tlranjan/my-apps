package com.dev.portal.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "VsphereCatalogModel")
public class VsphereCatalogModel extends DevPortalModel {

    private String template;
    private String endpoint;
    private String owner;
    private String published;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "VsphereCatalogModel [template=" + template + ", endpoint=" + endpoint + ", owner=" + owner
                + ", published=" + published + ", name=" + name + ", description=" + description + ", organization="
                + organization + "]";
    }

}
