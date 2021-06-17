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
import com.dev.portal.dao.client.RequestDaoClient;
import com.dev.portal.models.RequestModel;
import com.dev.portal.models.contants.PlatformType;

@RestController
public class RequestService {

	@Autowired
    MongoTemplate mongoTemplate;
	
	//not used
    @RequestMapping(value = AppURN.ADMIN_USER+"/getallrequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getAllRequestModel() {
        return new RequestDaoClient(mongoTemplate).getRequestModel();
    }
    
    @RequestMapping(value = AppURN.ADMIN_USER+"/getallawsrequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getAllAwsRequestModel() {
        return new RequestDaoClient(mongoTemplate).getRequestModelByPlatform(PlatformType.AMAZON_AWS.toString());
    }
    
    @RequestMapping(value = AppURN.ADMIN_USER+"/getallvsphererequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getAllVsphereRequestModel() {
        return new RequestDaoClient(mongoTemplate).getRequestModelByPlatform(PlatformType.VMWARE_VSPHERE.toString());
    }
    //not used
    @RequestMapping(value = AppURN.ADMIN_USER+"/getrequestmodelbyendpoint", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getRequestModelByEndpoint(@RequestParam String endpoint) {
        return new RequestDaoClient(mongoTemplate).getRequestModelByEndpoint(endpoint);
    }
    //not used
    @RequestMapping(value = AppURN.ADMIN_USER+"/addrequestmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel addRequestModel(@RequestBody RequestModel requestModel) {
        return new RequestDaoClient(mongoTemplate).addOrUpdateRequestModel(requestModel);
    }
    //not used
    @RequestMapping(value = AppURN.ADMIN_USER+"/updaterequestmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestModel updateRequestModel(@RequestBody RequestModel requestModel) {
        return new RequestDaoClient(mongoTemplate).addOrUpdateRequestModel(requestModel);
    }
    //not used
    @RequestMapping(value = AppURN.ADMIN_USER+"/deleterequestmodel", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteRequestModel(@RequestParam String id) {
        return new RequestDaoClient(mongoTemplate).deleteRequestModelById(id);
    }
    
	@RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorgsawsrequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getMyOrgsAwsRequestModel() {
        List<RequestModel> requestModelList = new ArrayList<>();
        RequestDaoClient requestDaoClient = new RequestDaoClient(mongoTemplate);
        List<String> organizations = new ServiceHelper().getMyOrganizations(mongoTemplate);
        for(String organization : organizations){
            requestModelList.addAll(requestDaoClient.getRequestModelByPlatformAndOrganization(PlatformType.AMAZON_AWS.toString(), organization));
        }
        return requestModelList;
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorgsvsphererequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getMyOrgsVsphereRequestModel() {
        List<RequestModel> requestModelList = new ArrayList<>();
        RequestDaoClient requestDaoClient = new RequestDaoClient(mongoTemplate);
        List<String> organizations = new ServiceHelper().getMyOrganizations(mongoTemplate);
        for(String organization : organizations){
            requestModelList.addAll(requestDaoClient.getRequestModelByPlatformAndOrganization(PlatformType.VMWARE_VSPHERE.toString(), organization));
        }
        return requestModelList;
    }
    
  //not in use
    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/getmyorgrequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getMyOrgRequestModel() {
        return new RequestDaoClient(mongoTemplate).getRequestModelByOrganization(new ServiceHelper().getMyOrganization(mongoTemplate));
    }
  
    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/getmyorgawsrequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getMyOrgAwsRequestModel() {
        return new RequestDaoClient(mongoTemplate).getRequestModelByPlatformAndOrganization(PlatformType.AMAZON_AWS.toString(), new ServiceHelper().getMyOrganization(mongoTemplate));
    }
  
    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/getmyorgvsphererequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getMyOrgVsphereRequestModel() {
        return new RequestDaoClient(mongoTemplate).getRequestModelByPlatformAndOrganization(PlatformType.VMWARE_VSPHERE.toString(), new ServiceHelper().getMyOrganization(mongoTemplate));
    }
    
  //not in use
    @RequestMapping(value = AppURN.DEVELOPER_USER+"/getmyrequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getMyRequestModel() {
        return new RequestDaoClient(mongoTemplate).getRequestModelByOwner(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    
    @RequestMapping(value = AppURN.DEVELOPER_USER+"/getmyawsrequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getMyAwsRequestModel() {
        System.out.println("getMyAwsRequestModel");
        return new RequestDaoClient(mongoTemplate).getRequestModelByPlatformAndOwner(PlatformType.AMAZON_AWS.toString(), SecurityContextHolder.getContext().getAuthentication().getName());
    }
    
    @RequestMapping(value = AppURN.DEVELOPER_USER+"/getmyvsphererequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getMyVsphereRequestModel() {
        System.out.println("getMyVsphereRequestModel");
        return new RequestDaoClient(mongoTemplate).getRequestModelByPlatformAndOwner(PlatformType.VMWARE_VSPHERE.toString(), SecurityContextHolder.getContext().getAuthentication().getName());
    }
    
}
