package kr.texturized.muus.common.storage;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for simple contents storage such as S3, Object Storage(Naver), Cloud Storage(GCP), etc.
 * Storage would be changed for several issues. So let it design with interface for flexible change.
 */
public interface PostImageStorage {

    public String getStorageDomain();
    public String upload(final Long userId, final MultipartFile image);
}
