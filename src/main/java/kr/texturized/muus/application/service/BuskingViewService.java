package kr.texturized.muus.application.service;

import java.util.List;
import kr.texturized.muus.dao.BuskingFindDao;
import kr.texturized.muus.domain.vo.BuskingMapVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for busking search.
 */
@Service
@RequiredArgsConstructor
public class BuskingViewService {

    private final BuskingFindDao buskingFindDao;

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
        final double latitude,
        final double longitude,
        final double widthMeter,
        final double heightMeter
    ) {
        return buskingFindDao.getActiveBuskingsInMap(latitude, longitude, widthMeter, heightMeter);
    }
}
