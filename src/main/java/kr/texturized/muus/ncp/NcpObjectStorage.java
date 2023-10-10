package kr.texturized.muus.ncp;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.IOException;
import kr.texturized.muus.common.error.exception.BusinessException;
import kr.texturized.muus.common.error.exception.ErrorCode;
import kr.texturized.muus.common.storage.PostImageStorage;
import org.apache.http.entity.ContentType;
import org.springframework.web.multipart.MultipartFile;

/**
 * Ncp Object Storage for post images.
 */
public class NcpObjectStorage implements PostImageStorage {

    private AmazonS3Client client;

    private String endPoint;

    private String bucketName;

    @Override
    public String upload(final MultipartFile image) {
        try {
            File uploadFile = convert(image)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File conversion failed."));

            client.putObject(new PutObjectRequest(bucketName, image.getOriginalFilename(), uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));

            return client.getUrl(bucketName, image.getOriginalFilename()).toString();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
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
        this.endPoint = endPoint;
        this.bucketName = bucketName;
    }


}
