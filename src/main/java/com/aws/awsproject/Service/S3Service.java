package com.aws.awsproject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.access-key-id}")
    private String accessKeyId;

    @Value("${aws.secret-access-key}")
    private String secretAccessKey;

    @Value("${aws.session-token:#{null}}")
    private String sessionToken;

    private S3Client getS3Client() {
        if (sessionToken != null && !sessionToken.isEmpty()) {
            AwsSessionCredentials credentials = AwsSessionCredentials.create(
                    accessKeyId,
                    secretAccessKey,
                    sessionToken
            );
            return S3Client.builder()
                    .region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(credentials))
                    .build();
        } else {
            AwsBasicCredentials credentials = AwsBasicCredentials.create(
                    accessKeyId,
                    secretAccessKey
            );
            return S3Client.builder()
                    .region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(credentials))
                    .build();
        }
    }

    public String uploadFile(MultipartFile file, Integer studentId) throws IOException {
        String fileName = "alumnos/" + studentId + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        S3Client s3Client = getS3Client();

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .contentType(file.getContentType())
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            return String.format("https://%s.s3.%s.amazonaws.com/%s",
                    bucketName, region, fileName);

        } finally {
            s3Client.close();
        }
    }
}
