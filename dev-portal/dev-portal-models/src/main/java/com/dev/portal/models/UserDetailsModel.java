package com.dev.portal.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserDetailsModel")
public class UserDetailsModel extends DevPortalModel {

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDetailsModel [role=" + role + ", name=" + name + ", description=" + description + ", organization="
                + organization + "]";
    }

}
