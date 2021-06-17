package com.dev.portal.helpers.aws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.AvailabilityZone;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

public class A1 {
    public static void main(String[] args) {
        List<Regions> listRegions = new ArrayList<>();
        //listRegions.add(Regions.US_EAST_1);
        listRegions.add(Regions.US_WEST_2);
        AmazonEC2ClientBuilder amazonEC2ClientBuilder = AmazonEC2ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("access_key", "secret_key")));
        Iterator<Regions> it = listRegions.iterator();
        while(it.hasNext()){            
            amazonEC2ClientBuilder = amazonEC2ClientBuilder.withRegion(it.next());
        }
        AmazonEC2 ec2 = amazonEC2ClientBuilder.build();
        boolean done = false;

        DescribeRegionsResult regions_response = ec2.describeRegions();

        for(com.amazonaws.services.ec2.model.Region region : regions_response.getRegions()) {
            System.out.printf(
                "Found region %s " +
                "with endpoint %s",
                region.getRegionName(),
                region.getEndpoint());
            System.out.println("");
        }
        DescribeImagesRequest describeImagesRequest = new DescribeImagesRequest();
        describeImagesRequest.withImageIds("ami-f2d3638a");
        DescribeImagesResult describeImagesResult = ec2.describeImages(describeImagesRequest);
        for(Image image: describeImagesResult.getImages()){
            System.out.println(image);
        }
       
        while(!done) {
            DescribeInstancesRequest request = new DescribeInstancesRequest();
            DescribeInstancesResult response = ec2.describeInstances(request);

            for(Reservation reservation : response.getReservations()) {
                for(Instance instance : reservation.getInstances()) {
                    System.out.printf(
                        "Found reservation with id %s, " +
                        "AMI %s, " +
                        "type %s, " +
                        "state %s " +
                        "and monitoring state %s",
                        instance.getInstanceId(),
                        instance.getImageId(),
                        instance.getInstanceType(),
                        instance.getState().getName(),
                        instance.getMonitoring().getState());
                    System.out.println("");
                }
            }
            done = true;
//            request.setNextToken(response.getNextToken());
//
//            if(response.getNextToken() == null) {
//                done = true;
//            }
        }
        
        
      
        
    }
}
