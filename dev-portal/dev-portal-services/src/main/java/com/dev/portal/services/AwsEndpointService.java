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

import com.amazonaws.regions.Regions;
import com.dev.portal.commons.ServiceHelper;
import com.dev.portal.constants.AppURN;
import com.dev.portal.dao.client.AwsEndpointDaoClient;
import com.dev.portal.helpers.aws.AwsClientHelper;
import com.dev.portal.helpers.aws.AwsResourceHelper;
import com.dev.portal.models.AwsEndpointModel;

@RestController
public class AwsEndpointService {

	@Autowired
    MongoTemplate mongoTemplate;
    
    @RequestMapping(value = AppURN.ADMIN_USER+"/getallawsendpointmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AwsEndpointModel> getAllAwsEndpointModel() {
        return new AwsEndpointDaoClient(mongoTemplate).getAwsEndpointModel();
    }
    
    @RequestMapping(value = AppURN.ADMIN_USER+"/getallawsendpointnames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllAwsEndpointNames() {
        List<String> endpointNames = new ArrayList<>();
        List<AwsEndpointModel> awsEndpointModelList = getAllAwsEndpointModel();
        for(AwsEndpointModel awsEndpointModel: awsEndpointModelList){
            endpointNames.add(awsEndpointModel.getName());
        }
        return endpointNames;
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorgsawsendpointmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AwsEndpointModel> getMyOrgsAwsEndpointModel() {
        List<AwsEndpointModel> awsEndpointModelList = new ArrayList<>();
        List<String> organizations = new ServiceHelper().getMyOrganizations(mongoTemplate);
        AwsEndpointDaoClient awsEndpointDaoClient = new AwsEndpointDaoClient(mongoTemplate);
        for(String organization : organizations){
            awsEndpointModelList.addAll(awsEndpointDaoClient.getAwsEndpointModelByOrganization(organization));
        }
        return awsEndpointModelList;
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getmyorgsawsendpointnames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMyOrgsAwsEndpointNames() {
        List<String> endpointNames = new ArrayList<>();
        List<AwsEndpointModel> awsEndpointModelList = getMyOrgsAwsEndpointModel();
        for(AwsEndpointModel awsEndpointModel : awsEndpointModelList){
            endpointNames.add(awsEndpointModel.getName());
        }
        return endpointNames;
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/addawsendpointmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AwsEndpointModel addAwsEndpointModel(@RequestBody AwsEndpointModel awsEndpointModel) {
        return new AwsEndpointDaoClient(mongoTemplate).addOrUpdateAwsEndpointModel(awsEndpointModel);
    }

    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/updateawsendpointmodel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AwsEndpointModel updateAwsEndpointModel(@RequestBody AwsEndpointModel awsEndpointModel) {
        return new AwsEndpointDaoClient(mongoTemplate).addOrUpdateAwsEndpointModel(awsEndpointModel);
    }

    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/deleteawsendpointmodel", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteAwsEndpointModel(@RequestParam String id) {
        return new AwsEndpointDaoClient(mongoTemplate).deleteAwsEndpointModelById(id);
    }
    
    @RequestMapping(value = AppURN.ORG_ADMIN_USER+"/getawscomputes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAwsComputes(@RequestBody AwsEndpointModel awsEndpointModel) {
        return new AwsResourceHelper().getAwsComputes(
                new AwsClientHelper(awsEndpointModel.getAccessKey(), awsEndpointModel.getSecretKey(), Regions.US_WEST_2)
                        .getAmazonEC2Client());
    }
	
	@RequestMapping(value = AppURN.DEVELOPER_USER+"/getmyorgawsendpointmodel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AwsEndpointModel> getMyOrgAwsEndpointModel() {
        return new AwsEndpointDaoClient(mongoTemplate).getAwsEndpointModelByOrganization(new ServiceHelper().getMyOrganization(mongoTemplate));
    }
    
    @RequestMapping(value = AppURN.CATALOG_ADMIN_USER+"/getmyorgawsendpointnames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMyOrgAwsEndpointNames() {
        List<String> endpointNames = new ArrayList<>();
        for(AwsEndpointModel awsEndpointModel : getMyOrgAwsEndpointModel()){
            endpointNames.add(awsEndpointModel.getName());
        }
        return endpointNames;
    }
    
}
