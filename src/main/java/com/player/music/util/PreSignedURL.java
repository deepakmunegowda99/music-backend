package com.player.music.util;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

public class PreSignedURL {

    public static String generateURL(String key) throws IOException {
        URL url = null;
        try {
            AmazonS3 s3Client = new AwsMakeConnection().returnClient();
            // set 2 mins
            final Date expirationTime = new Date(System.currentTimeMillis() + 2 * 60 * 1000);

            final GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(
                    "deepakbucket99999", key)
                    .withMethod(HttpMethod.PUT).withExpiration(expirationTime);
                            
            url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
        return url.toExternalForm();
    }
}
