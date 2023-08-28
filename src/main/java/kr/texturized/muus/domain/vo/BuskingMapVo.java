package kr.texturized.muus.domain.vo;

/**
 * Value object for marking on map.
 *
 * @param id Busking ID
 * @param latitude Latitude
 * @param longitude Longitude
 */
public record BuskingMapVo(
    Long id,
    Double latitude,
    Double longitude
) {

}
