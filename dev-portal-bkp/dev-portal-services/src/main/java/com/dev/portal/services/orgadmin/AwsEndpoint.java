package com.dev.portal.services.orgadmin;

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
import com.dev.portal.constants.AppURN;
import com.dev.portal.dao.client.AwsEndpointDaoClient;
import com.dev.portal.helpers.aws.AwsClientHelper;
import com.dev.portal.helpers.aws.AwsResourceHelper;
import com.dev.portal.models.AwsEndpointModel;

@RestController
@RequestMapping(AppURN.ORG_ADMIN_USER)
public class AwsEndpoint {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/getallawsendpoint", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AwsEndpointModel> getAllAwsEndpoint() {
        return new AwsEndpointDaoClient(mongoTemplate).getAllAwsEndpointModel();
    }
    
    @RequestMapping(value = "/getallawsendpointnames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllAwsEndpointNames() {
        List<String> awsEndpointNames = new ArrayList<>();
        List<AwsEndpointModel> awsEndpointModelList = new AwsEndpointDaoClient(mongoTemplate).getAllAwsEndpointModel();
        for(AwsEndpointModel awsEndpointModel : awsEndpointModelList){
            awsEndpointNames.add(awsEndpointModel.getEndpointName());
        }
        return awsEndpointNames;
    }

    @RequestMapping(value = "/addawsendpoint", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AwsEndpointModel addAwsEndpoint(@RequestBody AwsEndpointModel awsEndpointModel) {
        return new AwsEndpointDaoClient(mongoTemplate).addOrUpdateAwsEndpointModel(awsEndpointModel);
    }

    @RequestMapping(value = "/updateawsendpoint", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AwsEndpointModel updateAwsEndpoint(@RequestBody AwsEndpointModel awsEndpointModel) {
        return new AwsEndpointDaoClient(mongoTemplate).addOrUpdateAwsEndpointModel(awsEndpointModel);
    }

    @RequestMapping(value = "/deleteawsendpoint", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteAwsEndpoint(@RequestParam String id) {
        return new AwsEndpointDaoClient(mongoTemplate).deleteAwsEndpointModel(id);
    }

    @RequestMapping(value = "/getawscomputes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAwsComputes(@RequestBody AwsEndpointModel awsEndpointModel) {
        return new AwsResourceHelper().getAwsComputes(
                new AwsClientHelper(awsEndpointModel.getAccessKey(), awsEndpointModel.getSecretKey(), Regions.US_WEST_2)
                        .getAmazonEC2Client());
    }
    
    @RequestMapping(value = "/getawsregions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAwsRegions() {
        List<String> list = new ArrayList<String>();
        for (Regions region : Regions.values()) {
            list.add(region.toString());
        }
        return list;
    }

}
