package kr.texturized.muus.common.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for simple contents storage such as S3, Object Storage(Naver), Cloud Storage(GCP), etc.
 * Storage would be changed for several issues. So let it design with interface for flexible change.
 */
public interface PostImageStorage {

    String upload(final MultipartFile image);

    default Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
