package com.dev.portal.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dev.portal.dao.client.UserAuthDaoClient;
import com.dev.portal.models.UserAuthModel;

public class ServiceHelper {

    @Autowired
    MongoTemplate mongoTemplate;

    public UserAuthModel updateSelfPassword(String password) {
        return new UserAuthDaoClient(mongoTemplate).updateUserPassword(getLoggedInUser(), password);
    }

    public static String getLoggedInUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
