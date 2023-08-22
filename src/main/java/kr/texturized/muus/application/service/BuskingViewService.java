package kr.texturized.muus.application.service;

import java.util.List;
import kr.texturized.muus.application.service.calculator.CoordinateCalculator;
import kr.texturized.muus.domain.vo.BuskingMapVo;
import kr.texturized.muus.infrastructure.mapper.BuskingViewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for busking search.
 */
@Service
@RequiredArgsConstructor
public class BuskingViewService {

    private final CoordinateCalculator coordinateCalculator;
    private final BuskingViewMapper buskingViewMapper;

    /**
     * Get 'active' buskings list for map.
     *
     * @param latitude latitude
     * @param longitude longitude
     * @param widthMeter range of width(meter)
     * @param heightMeter range of height(meter)
     * @return list of active busking information for map
     */
    public List<BuskingMapVo> getActiveBuskingsInMap(
        double latitude,
        double longitude,
        double widthMeter,
        double heightMeter
    ) {
        return buskingViewMapper.getActiveBuskingsInMap(
            latitude,
            longitude,
            coordinateCalculator.meterToLatitude(widthMeter),
            coordinateCalculator.meterToLongitude(heightMeter)
        );
    }
}
