package com.dev.portal.helpers.aws;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import com.amazonaws.services.ec2.model.Region;

public class AwsClientHelper {

    private String accessKey;
    private String secretKey;
    private Regions regions;

    public AwsClientHelper(String accessKey, String secretKey, Regions regions) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.regions = regions;
    }

    public AmazonEC2 getAmazonEC2Client() {
        return AmazonEC2ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(regions).build();
    }

    public List<String> getAwsAvailableRegions() {
        DescribeRegionsResult describeRegionsResult = getAmazonEC2Client().describeRegions();
        List<String> regionList = new ArrayList<>();
        for (Region region : describeRegionsResult.getRegions()) {
            regionList.add(region.getRegionName());
        }
        return regionList;
    }

}
