package com.dev.portal.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.portal.commons.ServiceHelper;
import com.dev.portal.constants.UserRoles;
import com.dev.portal.dao.client.UserDetailsDaoClient;
import com.dev.portal.models.UserAuthModel;

@RestController
public class UserCommons {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/getusermenu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, String> getUserMenu() {
        String ROLE_ = "ROLE_";
        List<String> roleList = Arrays.asList(new UserDetailsDaoClient(mongoTemplate).getUserDetailsModelByUsername(ServiceHelper.getLoggedInUser()).getUserRole());
        if(roleList.contains(ROLE_+UserRoles.ADMIN)){
            return getAdminMenu();
        } else if (roleList.contains(ROLE_+UserRoles.ORG_ADMIN)){
            return getOrgAdminMenu();
        }else if (roleList.contains(ROLE_+UserRoles.CATALOG_ADMIN)){
            return getCatalogAdminMenu();
        }else if (roleList.contains(ROLE_+UserRoles.DEVELOPER)){
            return getDeveloperMenu();
        }
        return new HashMap<>();
    }
    
    @RequestMapping(value = "/updateselfpassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAuthModel updateSelfPassword(@RequestBody UserAuthModel userAuthModel) {
        return new ServiceHelper().updateSelfPassword(userAuthModel.getPassword());
    }
    
    @RequestMapping(value = "/getallroles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String[] getAllRoles() {
        String ROLE_ = "ROLE_";
        return new String[]{ ROLE_+UserRoles.ADMIN, ROLE_+UserRoles.ORG_ADMIN, ROLE_+UserRoles.CATALOG_ADMIN, ROLE_+UserRoles.DEVELOPER };
    }
    
    @RequestMapping(value = "/getbooleanvalues", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String[] getBooleanValues() {
        return new String[]{ "True", "False" };
    }

    private HashMap<String, String> getAdminMenu() {
        HashMap<String, String> menu = new LinkedHashMap<>();
        menu.put("manage_all_orgs", "Organizations");
        menu.put("manage_all_users", "Users");
        menu.put("manage_all_endpoints", "Endpoints");
        menu.put("manage_all_catalogs", "Catalogs");
        menu.put("all_requests", "Requests");
        menu.put("manage_all_machines", "Machines");
        return menu;
    }

    private HashMap<String, String> getOrgAdminMenu() {
        HashMap<String, String> menu = new LinkedHashMap<>();
        menu.put("manage_my_org", "Organization");
        menu.put("manage_my_endpoints", "Endpoints");
        menu.put("manage_myorg_catalogs", "Catalogs");
        menu.put("my_org_requests", "Requests");
        menu.put("manage_myorg_machines", "Machines");
        return menu;
    }

    private HashMap<String, String> getCatalogAdminMenu() {
        HashMap<String, String> menu = new LinkedHashMap<>();
        menu.put("manage_myorg_catalogs", "Catalogs");
        menu.put("my_org_requests", "Requests");
        menu.put("manage_my_machines", "Machines");
        return menu;
    }

    private HashMap<String, String> getDeveloperMenu() {
        HashMap<String, String> menu = new LinkedHashMap<>();
        menu.put("published_orgs_catalogs", "Catalogs");
        menu.put("my_requests", "Requests");
        menu.put("manage_my_machines", "Machines");
        return menu;
    }

}
