package com.player.music.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AwsMakeConnection {

    public AmazonS3 returnClient() {
        AWSCredentials credentials = new BasicAWSCredentials("AKIAJZE3L7GAUXZUC32A",
                "CBydyf2stGa/sUxk51DEFEisl4YY9MZN2aiKfX+J");

        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2).build();

        return s3client;

    }
    // Access Key ID:
    // AKIAJZE3L7GAUXZUC32A
    // Secret Access Key:
    // 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
}