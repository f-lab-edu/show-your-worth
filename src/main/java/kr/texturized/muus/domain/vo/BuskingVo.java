package kr.texturized.muus.domain.vo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * VO for busking.
 * Usage is for creation, update
 */
public record BuskingVo(
    String title,
    List<String> imagePaths,
    Double latitude,
    Double longitude,
    List<String> keywords,
    String description,
    LocalDateTime managedStartTime,
    LocalDateTime managedEndTime
) {

}
