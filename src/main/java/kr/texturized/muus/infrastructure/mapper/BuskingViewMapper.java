package kr.texturized.muus.infrastructure.mapper;

import java.util.List;
import kr.texturized.muus.domain.vo.BuskingMapVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * DAO for Busking Selection.
 */
@Mapper
public interface BuskingViewMapper {

    /**
     * Get Active buskings for map.
     * Since this method is basic showing in map, it should be ready to start or already started
     *
     * @param latitude Offset latitude
     * @param longitude Offset longitude
     * @param width Range of width to show, it should be converted to latitude
     * @param height Range of height to show, it should be converted to longitude
     * @return List of Busking, just id and coordinate
     */
    List<BuskingMapVo> getActiveBuskingsInMap(
        double latitude,
        double longitude,
        double width,
        double height
    );
}
