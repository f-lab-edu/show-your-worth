package kr.texturized.muus.common.storage;

import java.util.UUID;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for simple contents storage such as S3, Object Storage(Naver), Cloud Storage(GCP), etc.
 * Storage would be changed for several issues. So let it design with interface for flexible change.
 */
public interface PostImageStorage {

    /**
     * Upload an image in Storage.
     * Image will be uploaded in path {Storage Domain}/{id}/{Image File}.
     * {Image File} may be changed using {@code getImageName}(Optional.).
     *
     * @param userId Owner ID of image
     * @param image Image to upload
     * @return {userId}/{Uploaded Image File Name} or Empty String for some problem
     */
    String upload(final Long userId, final MultipartFile image);

    default String getImageName(MultipartFile image) {
        String ext = StringUtils.getFilenameExtension(image.getOriginalFilename());
        return UUID.randomUUID() + "-" + System.currentTimeMillis() + "." + ext;
    }
}
