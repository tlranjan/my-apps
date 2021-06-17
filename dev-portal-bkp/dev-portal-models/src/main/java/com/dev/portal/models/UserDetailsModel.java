package com.dev.portal.models;

import org.springframework.data.mongodb.core.index.Indexed;

public class UserDetailsModel extends DevPortalModel {

    @Indexed(unique = true)
    private String username;
    private String organizationName;
    private String userRole;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserDetailsModel [username=" + username + ", organizationName=" + organizationName + ", userRole="
                + userRole + ", id=" + id + "]";
    }

}
