package com.dev.portal.services.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.portal.constants.AppURN;
import com.dev.portal.dao.client.MachineDaoClient;
import com.dev.portal.models.MachineModel;

@RestController
@RequestMapping(AppURN.ADMIN_USER)
public class ManageAllMachines {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/getallmachines", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MachineModel> getAllMachines() {
        return new MachineDaoClient(mongoTemplate).getAllMachineModel();
    }

}
