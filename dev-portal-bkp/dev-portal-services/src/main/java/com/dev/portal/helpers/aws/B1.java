package com.dev.portal.helpers.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.Instance;

public class B1 {
    public static void main(String[] args) throws InterruptedException {
        AmazonEC2 awsClientHelper = new AwsClientHelper("access_key", "secret_key", Regions.US_WEST_2).getAmazonEC2Client();
        AwsResourceHelper awsResourceHelper = new AwsResourceHelper();
        //RunInstancesResult s1 = awsResourceHelper.requestEC2(awsClientHelper, "ami-f2d3638a", "t2.micro", null, "default");
        Instance s1 = awsResourceHelper.requestEC2(awsClientHelper, "ami-f2d3638a-name3", "ami-f2d3638a", "t2.micro", "default");
        System.out.println(s1.getInstanceId());
        Thread.sleep(5*60*1000);
        Instance instance = awsResourceHelper.getEC2InstanceDetails(awsClientHelper, s1.getInstanceId());
        System.out.printf(
                "Found instance with id %s, " +
                "AMI %s, " +
                "type %s, " +
                "state %s " +
                "and monitoring state %s",
                instance.getInstanceId(),
                instance.getImageId(),
                instance.getInstanceType(),
                instance.getState().getName(),
                instance.getMonitoring().getState());
        System.out.println(instance.getTags().get(0).toString());
    }
}
