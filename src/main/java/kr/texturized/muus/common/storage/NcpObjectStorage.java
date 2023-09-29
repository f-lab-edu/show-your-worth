package kr.texturized.muus.common.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Ncp Object Storage for post images.
 */
public class NcpObjectStorage implements PostImageStorage {

    private AmazonS3Client client;

    @Override
    public String getStorageDomain() {
        return "";
    }

    @Override
    public String upload(Long userId, MultipartFile image) {
        return null;
    }

    public NcpObjectStorage(
        final String accessKey,
        final String secretKey,
        final String endPoint,
        final String region,
        final String bucketName
    ) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        this.client = (AmazonS3Client) AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .withEndpointConfiguration(new EndpointConfiguration(endPoint, region))
            .build();
    }
}
