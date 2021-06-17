package com.dev.portal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.portal.constants.AppURN;
import com.dev.portal.dao.client.UserAuthDaoClient;
import com.dev.portal.dao.client.UserDetailsDaoClient;
import com.dev.portal.models.UserAuthModel;
import com.dev.portal.models.UserDetailsModel;
import com.dev.portal.models.contants.UserRoles;

@RestController
public class UserAuthService {

	@Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = AppURN.ADMIN_USER+"/addnewuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsModel addNewUser(@RequestBody UserAuthModel userAuthModel) {
        UserDetailsModel userDetailsModel = new UserDetailsModel();
        userDetailsModel.setName(userAuthModel.getUsername());
        userDetailsModel.setRole("ROLE_"+UserRoles.DEVELOPER);
        UserAuthModel userAuthModelInDB = new UserAuthDaoClient(mongoTemplate).addOrUpdateUserAuthModel(userAuthModel);
        if (userAuthModelInDB.getUsername() != null) {
            return new UserDetailsDaoClient(mongoTemplate).addOrUpdateUserDetailsModel(userDetailsModel);
        } else {
            return null;
        }
    }
    
}
