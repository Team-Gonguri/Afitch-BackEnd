package com.project.configs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.project.exception.InvalidFileTypeException;
import com.project.exception.NotYourContentsException;
import com.project.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Manager {
    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.bucket}")
    private String bucketName;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.cloudfront}")
    private String cloudFrontURL;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String createdAt = DateUtils.parseDateToSimpleString(new Date());
        String fileName = String.format("%s-%s.mp4", createdAt, UUID.randomUUID().toString());
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectMetadata metadata = new ObjectMetadata();
        String contentType;

        if(ext.equals("mp4"))
            contentType = "video/mp4";
        else if(ext.equals("webm"))
            contentType = "video/webm";
        else
            throw new InvalidFileTypeException();

        metadata.setContentLength(bytes.length);
        metadata.setContentType(contentType);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, byteArrayInputStream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));


        String s3URL = s3Client.getUrl(bucketName, fileName).toString();
        return cloudFrontURL + (s3URL).substring(s3URL.lastIndexOf("/") + 1);
    }

    public void deleteFile(String url) {
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        if (s3Client.doesObjectExist(bucketName, fileName))
            s3Client.deleteObject(bucketName, fileName);
    }
}
