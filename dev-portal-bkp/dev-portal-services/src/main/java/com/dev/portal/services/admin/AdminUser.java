package com.dev.portal.services.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.portal.constants.AppURN;
import com.dev.portal.constants.UserRoles;
import com.dev.portal.dao.client.UserAuthDaoClient;
import com.dev.portal.dao.client.UserDetailsDaoClient;
import com.dev.portal.models.UserAuthModel;
import com.dev.portal.models.UserDetailsModel;

@RestController
@RequestMapping(AppURN.ADMIN_USER)
public class AdminUser {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/getuser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAuthModel getUser(@RequestParam String username) {
        return new UserAuthDaoClient(mongoTemplate).getUserAuthModel(username);
    }

    @RequestMapping(value = "/addnewuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsModel addNewUser(@RequestBody UserAuthModel userAuthModel) {
        UserDetailsModel userDetailsModel = new UserDetailsModel();
        userDetailsModel.setUsername(userAuthModel.getUsername());
        userDetailsModel.setOrganizationName("default");
        userDetailsModel.setUserRole(UserRoles.DEVELOPER);
        UserAuthModel userAuthModelInDB = new UserAuthDaoClient(mongoTemplate).addOrUpdateUserAuthModel(userAuthModel);
        if (userAuthModelInDB.getUsername() != null) {
            return new UserDetailsDaoClient(mongoTemplate).addOrUpdateUserDetailsModel(userDetailsModel);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAuthModel updateUser(@RequestBody UserAuthModel userAuthModel) {
        return new UserAuthDaoClient(mongoTemplate).addOrUpdateUserAuthModel(userAuthModel);
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteUser(@RequestBody UserAuthModel userAuthModel) {
        return new UserAuthDaoClient(mongoTemplate).deleteUserAuthModel(userAuthModel);
    }
    
    @RequestMapping(value = "/getuserdetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsModel getUserDetails(@RequestParam String username) {
        return new UserDetailsDaoClient(mongoTemplate).getUserDetailsModelByUsername(username);
    }
    
    @RequestMapping(value = "/getalluserdetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDetailsModel> getAllUserDetails() {
        return new UserDetailsDaoClient(mongoTemplate).getAllUserDetailsModel();
    }

    @RequestMapping(value = "/updateuserdetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsModel updateUserDetails(@RequestBody UserDetailsModel userDetailsModel) {
        return new UserDetailsDaoClient(mongoTemplate).addOrUpdateUserDetailsModel(userDetailsModel);
    }

    @RequestMapping(value = "/deleteuserdetails", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteUserDetails(@RequestBody UserDetailsModel userDetailsModel) {
        if(new UserDetailsDaoClient(mongoTemplate).deleteUserDetailsModel(userDetailsModel)){
            return new UserAuthDaoClient(mongoTemplate).deleteUserAuthModelByName(userDetailsModel.getUsername());
        } else {
            return false;
        }
    }

}
