package kr.texturized.muus.presentation.api.response;

/**
 * Response for busking to show in map.
 *
 * Since it is for showing in map, it just requires coordinate,
 * and id for show detail after selection.
 *
 * @param id Busking id
 * @param latitude Latitude of busking
 * @param longitude Longitude of busking
 */
public record BuskingsInMapResponse(
    long id,
    double latitude,
    double longitude
) {

}
