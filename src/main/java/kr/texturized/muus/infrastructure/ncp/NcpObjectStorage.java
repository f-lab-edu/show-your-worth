package kr.texturized.muus.infrastructure.ncp;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import kr.texturized.muus.common.storage.PostImageStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 * Ncp Object Storage for post images.
 */
@Slf4j
public class NcpObjectStorage implements PostImageStorage {

    private final AmazonS3Client client;

    private final String bucketName;

    @Override
    public String upload(final Long userId, final MultipartFile image) {
        try {
            final ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(image.getInputStream().available());

            final String fileName = userId + "/" + getImageName(image);

            client.putObject(bucketName, fileName, image.getInputStream(), metadata);

            return fileName;
        } catch (IOException e) {
            log.error("{}", e.getMessage());
            return "";
        } catch (AmazonServiceException e) {
            log.error("Error occurred in Amazon S3 while processing the request. "
                + "Caused By: {}", e.getMessage());
            return "";
        } catch (SdkClientException e) {
            log.error("Error occurred in WAS while making the request or handling the response. "
                + "Caused By: {}", e.getMessage());
            return "";
        }
    }

    /**
     * Constructor.
     *
     * @param accessKey accessKey
     * @param secretKey secretKey
     * @param endPoint endPoint
     * @param region region
     * @param bucketName bucketName
     */
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
        this.bucketName = bucketName;
    }

}
