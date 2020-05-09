////list both folder and songs within it

package com.player.music.util;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class ListEntities {

    AmazonS3 s3Client = new AwsMakeConnection().returnClient();

    public Object ListOfEntites(String key) {

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName("deepakbucket99999")
                .withPrefix(key);
        ObjectListing listing = null;
        if (key == null) {
            listObjectsRequest.withDelimiter("/");
            listing = s3Client.listObjects(listObjectsRequest);
            return listing.getCommonPrefixes();
        }
        listing = s3Client.listObjects(listObjectsRequest);
        List<S3ObjectSummary> summaries = listing.getObjectSummaries();
        while (listing.isTruncated()) {
            listing = s3Client.listNextBatchOfObjects(listing);
            summaries.addAll(listing.getObjectSummaries());
        }
        return summaries;
    }

}