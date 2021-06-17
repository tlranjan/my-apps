package com.dev.portal.services.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.portal.commons.ServiceHelper;
import com.dev.portal.dao.client.UserDetailsDaoClient;
import com.dev.portal.models.UserAuthModel;
import com.dev.portal.models.contants.Published;
import com.dev.portal.models.contants.UserRoles;

@RestController
public class UserCommons {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/getusermenu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, String> getUserMenu() {
        String ROLE_ = "ROLE_";
        List<String> roleList = Arrays.asList(new UserDetailsDaoClient(mongoTemplate).getUserDetailsModelByName(SecurityContextHolder.getContext().getAuthentication().getName()).getRole());
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
        return new ServiceHelper().updateSelfPassword(mongoTemplate, userAuthModel.getPassword());
    }
    
    @RequestMapping(value = "/getcatalogpublishvalues", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String[] getCatalogPublishValues() {
        return new String[]{ Published.YES.toString(), Published.NO.toString() };
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
        menu.put("manage_myorgs", "Organization");
        menu.put("manage_myorgs_user", "Users");
        menu.put("manage_myorgs_endpoint", "Endpoints");
        menu.put("manage_myorgs_catalog", "Catalogs");
        menu.put("my_orgs_request", "Requests");
        menu.put("manage_myorgs_machine", "Machines");
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
