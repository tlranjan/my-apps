package com.dev.portal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.portal.commons.CommonUtils;
import com.dev.portal.commons.RequestRunner;
import com.dev.portal.constants.AppURN;
import com.dev.portal.dao.client.AwsCatalogDaoClient;
import com.dev.portal.dao.client.RequestDaoClient;
import com.dev.portal.dao.client.VsphereCatalogDaoClient;
import com.dev.portal.models.AwsCatalogModel;
import com.dev.portal.models.RequestModel;
import com.dev.portal.models.VsphereCatalogModel;
import com.dev.portal.models.contants.PlatformType;
import com.dev.portal.models.contants.RequestStatus;
import com.dev.portal.models.ui.CatalogRequestModel;

@RestController
public class RequestsTask {

    @Autowired
    MongoTemplate mongoTemplate;
    
    @RequestMapping(value = AppURN.DEVELOPER_USER+"/requestawscatalog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> requestAwsCatalog(@RequestBody CatalogRequestModel catalogRequestModel) {
        List<RequestModel> requestModelList = new ArrayList<>();
        RequestDaoClient requestDaoClient = new RequestDaoClient(mongoTemplate);
        for(int i=0;i<Integer.parseInt(catalogRequestModel.getCount());i++){
            RequestModel requestModel = composeRequestModel(catalogRequestModel, PlatformType.AMAZON_AWS.toString());
            requestModel = requestDaoClient.addOrUpdateRequestModel(requestModel);
            RequestRunner requestRunner = new RequestRunner(mongoTemplate, requestModel);
            requestRunner.start();
            requestModelList.add(requestModel);
        }
        
        return requestModelList;
    }
    
    @RequestMapping(value = AppURN.DEVELOPER_USER+"/requestvspherecatalog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> requestVsphereCatalogs(@RequestBody CatalogRequestModel catalogRequestModel) {
        List<RequestModel> requestModelList = new ArrayList<>();
        RequestDaoClient requestDaoClient = new RequestDaoClient(mongoTemplate);
        for(int i=0;i<Integer.parseInt(catalogRequestModel.getCount());i++){
            RequestModel requestModel = composeRequestModel(catalogRequestModel, PlatformType.VMWARE_VSPHERE.toString());
            requestModel = requestDaoClient.addOrUpdateRequestModel(requestModel);
            RequestRunner requestRunner = new RequestRunner(mongoTemplate, requestModel);
            requestRunner.start();
            requestModelList.add(requestModel);
        }
        
        return requestModelList;
    }
    
    private RequestModel composeRequestModel(CatalogRequestModel catalogRequestModel, String platform){
        RequestModel requestModel = new RequestModel();
        requestModel.setName(catalogRequestModel.getName()+"-"+UUID.randomUUID());
        requestModel.setCatalog(catalogRequestModel.getCatalog());
        String organization = null;
        String endpoint = null;
        if(platform.equals(PlatformType.AMAZON_AWS.toString())){
            AwsCatalogModel awsCatalogModel = new AwsCatalogDaoClient(mongoTemplate).getAwsCatalogModelByName(catalogRequestModel.getCatalog());
            organization = awsCatalogModel.getOrganization();
            endpoint = awsCatalogModel.getEndpoint();
        } else if(platform.equals(PlatformType.VMWARE_VSPHERE.toString())){
            VsphereCatalogModel vsphereCatalogModel = new VsphereCatalogDaoClient(mongoTemplate).getVsphereCatalogModelByName(catalogRequestModel.getCatalog());
            organization = vsphereCatalogModel.getOrganization();
            endpoint = vsphereCatalogModel.getEndpoint();
        }
        requestModel.setOrganization(organization);
        requestModel.setEndpoint(endpoint);
        requestModel.setOwner(SecurityContextHolder.getContext().getAuthentication().getName());
        requestModel.setPlatform(platform);
        requestModel.setStatus(RequestStatus.IN_PROGRESS.toString());
        requestModel.setSubmittedTime(CommonUtils.getCurrentTime());
        requestModel.setLastUpdatedTime(CommonUtils.getCurrentTime());
        return requestModel;
    }
}
