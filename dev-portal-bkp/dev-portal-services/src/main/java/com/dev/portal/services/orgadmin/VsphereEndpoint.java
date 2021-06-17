package com.dev.portal.services.orgadmin;

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
import com.dev.portal.dao.client.VsphereEndpointDaoClient;
import com.dev.portal.helpers.vcenter.VsphereResourceHelper;
import com.dev.portal.models.VsphereEndpointModel;

@RestController
@RequestMapping(AppURN.ORG_ADMIN_USER)
public class VsphereEndpoint {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/getallvsphereendpoint", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VsphereEndpointModel> getAllVsphereEndpoint() {
        return new VsphereEndpointDaoClient(mongoTemplate).getAllVsphereEndpointModel();
    }

    @RequestMapping(value = "/addvsphereendpoint", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public VsphereEndpointModel addVsphereEndpoint(@RequestBody VsphereEndpointModel vsphereEndpointModel) {
        return new VsphereEndpointDaoClient(mongoTemplate).addOrUpdateVsphereEndpointModel(vsphereEndpointModel);
    }

    @RequestMapping(value = "/updatevsphereendpoint", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public VsphereEndpointModel updateVsphereEndpoint(@RequestBody VsphereEndpointModel vsphereEndpointModel) {
        return new VsphereEndpointDaoClient(mongoTemplate).addOrUpdateVsphereEndpointModel(vsphereEndpointModel);
    }

    @RequestMapping(value = "/deletevsphereendpoint", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteVsphereEndpoint(@RequestParam String id) {
        return new VsphereEndpointDaoClient(mongoTemplate).deleteVsphereEndpointModel(id);
    }

    @RequestMapping(value = "/getvspherecomputes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getVsphereComputes(@RequestBody VsphereEndpointModel vsphereEndpointModel) {
        return new VsphereResourceHelper(vsphereEndpointModel.getVcSdkUrl(), vsphereEndpointModel.getVcenterUser(),
                vsphereEndpointModel.getVcenterPassword()).getVsphereComputes();
    }

}
