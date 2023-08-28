package kr.texturized.muus.presentation.api;

import static java.util.stream.Collectors.*;

import java.util.List;
import kr.texturized.muus.application.service.BuskingViewService;
import kr.texturized.muus.common.coordinate.RangeChecker;
import kr.texturized.muus.domain.vo.BuskingMapVo;
import kr.texturized.muus.presentation.api.response.BuskingsInMapResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buskings")
@RequiredArgsConstructor
public class BuskingController {

    private final BuskingViewService buskingViewService;
    private final RangeChecker rangeChecker;

    /**
     * Get busking list to show in map.<br>
     *
     * @param latitude Offset latitude
     * @param longitude Offset longitude
     * @param widthMeter Range of meter(Width)
     * @param heightMeter Range of meter(Height)
     * @return List of busking
     */
    @GetMapping
    public List<BuskingsInMapResponse> getBuskingsInMap(
        @RequestParam double latitude,
        @RequestParam double longitude,
        @RequestParam double widthMeter,
        @RequestParam double heightMeter
    ) {
        rangeChecker.validateRange(latitude, longitude, widthMeter, heightMeter);

        return buskingViewService.getActiveBuskingsInMap(
                latitude,
                longitude,
                widthMeter,
                heightMeter
            ).stream()
            .map(this::dto)
            .collect(toList());
    }

    private BuskingsInMapResponse dto(BuskingMapVo vo) {
        return new BuskingsInMapResponse(vo.id(), vo.latitude(), vo.longitude());
    }
}
