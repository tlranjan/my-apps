package com.dev.portal.helpers.aws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Region;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.ResourceType;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.TagSpecification;

public class AwsResourceHelper {

    public List<String> getAwsComputes(AmazonEC2 amazonEC2) {
        DescribeRegionsResult describeRegionsResult = amazonEC2.describeRegions();
        List<String> regionList = new ArrayList<>();
        for (Region region : describeRegionsResult.getRegions()) {
            regionList.add(region.getRegionName());
        }
        return regionList;
    }

    public Instance requestEC2(AmazonEC2 amazonEC2, String instanceName, String imageId, String instanceType, String securityGroupName) {
        RunInstancesRequest runInstancesRequest = new RunInstancesRequest();
        TagSpecification tagSpecification = new TagSpecification();
        tagSpecification.withResourceType(ResourceType.Instance).withTags(new Tag().withKey("Name").withValue(instanceName));
        runInstancesRequest.withImageId(imageId).withInstanceType(instanceType).withMinCount(1).withMaxCount(1).withSecurityGroups(securityGroupName).withTagSpecifications(tagSpecification);
        RunInstancesResult runInstancesResult = amazonEC2.runInstances(runInstancesRequest);
        return runInstancesResult.getReservation().getInstances().get(0);
    }

    public List<String> getAMIDetails(AmazonEC2 amazonEC2, String amiId){
        DescribeImagesRequest describeImagesRequest = new DescribeImagesRequest();
        describeImagesRequest.withImageIds(amiId);
        DescribeImagesResult describeImagesResult = amazonEC2.describeImages(describeImagesRequest);
        List<String> amiDetails = new ArrayList<>();
        for(Image image: describeImagesResult.getImages()){
            amiDetails.add(image.toString());
        }
        return amiDetails;
    }
    
    public List<Instance> getEC2InstanceDetails(AmazonEC2 amazonEC2, List<String> instanceIds){
        List<Instance> instanceList = new ArrayList<>();
        DescribeInstancesRequest describeInstancesRequest = new DescribeInstancesRequest();
        describeInstancesRequest.setInstanceIds(instanceIds);
        DescribeInstancesResult describeInstancesResult = amazonEC2.describeInstances(describeInstancesRequest);
        for(Reservation reservation : describeInstancesResult.getReservations()){
            for(Instance instance : reservation.getInstances()) {
                instanceList.add(instance);
            }
        }
        return instanceList;
    }
    
    public Instance getEC2InstanceDetails(AmazonEC2 amazonEC2, String instanceId){
        return getEC2InstanceDetails(amazonEC2, Arrays.asList(instanceId)).get(0);
    }

}
