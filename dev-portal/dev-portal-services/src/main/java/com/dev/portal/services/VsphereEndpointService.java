package com.dev.portal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.portal.commons.ServiceHelper;
import com.dev.portal.constants.AppURN;
import com.dev.portal.dao.client.VsphereEndpointDaoClient;
import com.dev.portal.helpers.vcenter.VsphereResourceHelper;
import com.dev.portal.models.VsphereEndpointModel;

@RestController
public class VsphereEndpointService {

	@Autowired
    MongoTemplate mongoTemplate;
	
	@RequestMapping(value = AppURN.ADMIN_USER+"/getallvsphereendpointmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VsphereEndpointModel> getAllVsphereEndpointModel() {
        return new VsphereEndpointDaoClient(mongoTemplate).getVsphereEndpointModel();
    }

    @RequestMapping(value = AppURN.ADMIN_USER+"/getallvsphereendpointnames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllVsphereEndpointNames() {
        List<String> endpointNames = new ArrayList<>();
        List<VsphereEndpointModel> vsphereEndpointModelList = getAllVsphereEndpointModel();
        for(VsphereEndpointModel vsphereEndpointModel: vsphereEndpointModelList){
            endpointNames.add(vsphereEndpointModel.getName());
        }
        return endpointNames;
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorgsvsphereendpointmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VsphereEndpointModel> getMyOrgsVsphereEndpointModel() {
        List<VsphereEndpointModel> vsphereEndpointModelList = new ArrayList<>();
        List<String> organizations = new ServiceHelper().getMyOrganizations(mongoTemplate);
        VsphereEndpointDaoClient vsphereEndpointDaoClient = new VsphereEndpointDaoClient(mongoTemplate);
        for(String organization : organizations){
            vsphereEndpointModelList.addAll(vsphereEndpointDaoClient.getVsphereEndpointModelByOrganization(organization));
        }
        return vsphereEndpointModelList;
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorgsvsphereendpointnames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMyOrgsVsphereEndpointNames() {
        List<String> endpointNames = new ArrayList<>();
        List<VsphereEndpointModel> vsphereEndpointModelList = getMyOrgsVsphereEndpointModel();
        for(VsphereEndpointModel vsphereEndpointModel : vsphereEndpointModelList){
            endpointNames.add(vsphereEndpointModel.getName());
        }
        return endpointNames;
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/addvsphereendpointmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public VsphereEndpointModel addVsphereEndpointModel(@RequestBody VsphereEndpointModel vsphereEndpointModel) {
        return new VsphereEndpointDaoClient(mongoTemplate).addOrUpdateVsphereEndpointModel(vsphereEndpointModel);
    }

    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/updatevsphereendpointmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public VsphereEndpointModel updateVsphereEndpointModel(@RequestBody VsphereEndpointModel vsphereEndpointModel) {
        return new VsphereEndpointDaoClient(mongoTemplate).addOrUpdateVsphereEndpointModel(vsphereEndpointModel);
    }

    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/deletevsphereendpointmodel", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteVsphereEndpointModel(@RequestParam String id) {
        return new VsphereEndpointDaoClient(mongoTemplate).deleteVsphereEndpointModelById(id);
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getvspherecomputes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getVsphereComputes(@RequestBody VsphereEndpointModel vsphereEndpointModel) {
        return new VsphereResourceHelper(vsphereEndpointModel.getSdkUrl(), vsphereEndpointModel.getUser(),
                vsphereEndpointModel.getPassword()).getVsphereComputes();
    }
	
	@RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/getmyorgvsphereendpointnames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMyOrgVsphereEndpointNames() {
		List<String> endpointNames = new ArrayList<>();
		for(VsphereEndpointModel vsphereEndpointModel : getMyOrgVsphereEndpointModel()){
			endpointNames.add(vsphereEndpointModel.getName());
		}
        return endpointNames;
    }
	
	@RequestMapping(value = AppURN.DEVELOPER_USER+"/getmyorgvsphereendpointmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VsphereEndpointModel> getMyOrgVsphereEndpointModel() {
        return new VsphereEndpointDaoClient(mongoTemplate).getVsphereEndpointModelByOrganization(new ServiceHelper().getMyOrganization(mongoTemplate));
    }
}
