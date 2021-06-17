package com.dev.portal.services.developer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.portal.commons.CommonUtils;
import com.dev.portal.commons.RequestRunner;
import com.dev.portal.constants.AppURN;
import com.dev.portal.constants.PlatformType;
import com.dev.portal.dao.client.RequestDaoClient;
import com.dev.portal.models.CatalogRequestModel;
import com.dev.portal.models.RequestModel;

@RestController
@RequestMapping(AppURN.DEVELOPER_USER)
public class Requests {

    @Autowired
    MongoTemplate mongoTemplate;
    
    @RequestMapping(value = "/requestawscatalogs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> requestAwsCatalog(@RequestBody CatalogRequestModel catalogRequestModel) {
        for(int i=0;i<Integer.parseInt(catalogRequestModel.getRequestCount());i++){
            RequestRunner requestRunner = new RequestRunner(mongoTemplate, catalogRequestModel, PlatformType.AMAZON_AWS.toString());
            requestRunner.start();
        }
        CommonUtils.sleepSeconds(3);
        return new RequestDaoClient(mongoTemplate).getRequestModelByName(catalogRequestModel.getRequestName());
    }
    
    @RequestMapping(value = "/requestvspherecatalogs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> requestVsphereCatalogs(@RequestBody CatalogRequestModel catalogRequestModel) {
        for(int i=0;i<Integer.parseInt(catalogRequestModel.getRequestCount());i++){
            RequestRunner requestRunner = new RequestRunner(mongoTemplate, catalogRequestModel, PlatformType.VMWARE_VSPHERE.toString());
            requestRunner.start();
        }
        CommonUtils.sleepSeconds(3);
        return new RequestDaoClient(mongoTemplate).getRequestModelByName(catalogRequestModel.getRequestName());
    }
    
    @RequestMapping(value = "/getallawsrequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getAllAwsRequestModel() {
        return new RequestDaoClient(mongoTemplate).getRequestModelByPlatformType(PlatformType.AMAZON_AWS.toString());
    }
    
    @RequestMapping(value = "/getallvsphererequestmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestModel> getAllVsphereRequestModel() {
        return new RequestDaoClient(mongoTemplate).getRequestModelByPlatformType(PlatformType.VMWARE_VSPHERE.toString());
    }
    
}
