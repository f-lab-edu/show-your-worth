package kr.texturized.muus.presentation.api.request;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * Request for busking create, update.
 */
public record BuskingRequest (
    String title,
    List<MultipartFile> imageFiles,
    Double latitude,
    Double longitude,
    List<String> keywords,
    String description,
    LocalDateTime managedStartTime,
    LocalDateTime managedEndTime
){

}
