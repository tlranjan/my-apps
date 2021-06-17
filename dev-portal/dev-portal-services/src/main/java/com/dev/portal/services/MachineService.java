package com.dev.portal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.portal.commons.ServiceHelper;
import com.dev.portal.constants.AppURN;
import com.dev.portal.dao.client.MachineDaoClient;
import com.dev.portal.models.MachineModel;

@RestController
public class MachineService {

	@Autowired
    MongoTemplate mongoTemplate;
	
	@RequestMapping(value = AppURN.ADMIN_USER+"/getallmachinemodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MachineModel> getAllMachineModel() {
        return new MachineDaoClient(mongoTemplate).getMachineModel();
    }
    //not used
    @RequestMapping(value = AppURN.ADMIN_USER+"/getmachinemodelbyendpoint", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MachineModel> getMachineModelByEndpoint(@RequestParam String endpoint) {
        return new MachineDaoClient(mongoTemplate).getMachineModelByEndpoint(endpoint);
    }
    //not used
    @RequestMapping(value = AppURN.ADMIN_USER+"/addmachinemodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public MachineModel addMachineModel(@RequestBody MachineModel machineModel) {
        return new MachineDaoClient(mongoTemplate).addOrUpdateMachineModel(machineModel);
    }
    //not used
    @RequestMapping(value = AppURN.ADMIN_USER+"/updatemachinemodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public MachineModel updateMachineModel(@RequestBody MachineModel machineModel) {
        return new MachineDaoClient(mongoTemplate).addOrUpdateMachineModel(machineModel);
    }
    //not used
    @RequestMapping(value = AppURN.ADMIN_USER+"/deletemachinemodel", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteMachineModel(@RequestParam String id) {
        return new MachineDaoClient(mongoTemplate).deleteMachineModelById(id);
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorgsmachinemodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MachineModel> getMyOrgsMachineModel() {
        List<String> organizations = new ServiceHelper().getMyOrganizations(mongoTemplate);
        MachineDaoClient machineDaoClient = new MachineDaoClient(mongoTemplate);
        List<MachineModel> machineModelList = new ArrayList<>();
        for(String organization : organizations){
            machineModelList.addAll(machineDaoClient.getMachineModelByOrganization(organization));
        }
        return machineModelList;
    }
    
  //used
    @RequestMapping(value = AppURN.DEVELOPER_USER+"/getmymachinemodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MachineModel> getMymachineModel() {
        return new MachineDaoClient(mongoTemplate).getMachineModelByOwner(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    
    //request a published org catalog
    //power off a my machine
    //power on a my machine
    //destroy a my machine
    
}
