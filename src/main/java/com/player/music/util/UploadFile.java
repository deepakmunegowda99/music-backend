package com.player.music.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.player.music.exception.BadRequestException;

public class UploadFile {
    AmazonS3 s3Client = new AwsMakeConnection().returnClient();

    public String UploadImage(String image, String imageName) {

        String imageType = image.substring(image.indexOf(":") + 1);
        imageType = imageType.substring(0, imageType.indexOf(";"));
        byte[] bI = org.apache.commons.codec.binary.Base64
                .decodeBase64((image.substring(image.indexOf(",") + 1)).getBytes());
        InputStream fis = new ByteArrayInputStream(bI);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bI.length);
        metadata.setContentType(imageType);
        metadata.setCacheControl("public, max-age=31536000");
        s3Client.putObject("deepakbucket99999", "images/" + imageName + "1", fis, metadata);
        URL url = s3Client.getUrl("deepakbucket99999", "images/" + imageName + "1");
        return url.toString();
    }

    public void MoveObject(String path) {
        try {
            s3Client.copyObject("deepakbucket99999", path, "deepakbucket99999", "used");
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    public void DeleteObject(String path) {
        s3Client.deleteObject("deepakbucket99999", "unused/" + path);
    }

}
