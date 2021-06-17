package com.dev.portal.models;

import org.springframework.data.mongodb.core.index.Indexed;

public class UserAuthModel extends DevPortalModel {

    @Indexed(unique = true)
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserAuthModel [username=" + username + ", password=" + password + ", id=" + id + "]";
    }

}
