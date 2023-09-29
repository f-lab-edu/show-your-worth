package kr.texturized.muus.presentation.api;

import static java.util.stream.Collectors.*;

import java.util.List;
import javax.validation.Valid;
import kr.texturized.muus.application.service.BuskingService;
import kr.texturized.muus.application.service.BuskingViewService;
import kr.texturized.muus.common.coordinate.RangeChecker;
import kr.texturized.muus.domain.vo.BuskingMapVo;
import kr.texturized.muus.domain.vo.CreateBuskingVo;
import kr.texturized.muus.presentation.api.request.BuskingRequest;
import kr.texturized.muus.presentation.api.response.BuskingsInMapResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buskings")
@RequiredArgsConstructor
public class BuskingController {

    private final RangeChecker rangeChecker;
    private final BuskingViewService buskingViewService;
    private final BuskingService buskingService;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}")
    public Long createBusking(
        @RequestParam final Long userId,
        @Valid @RequestBody final BuskingRequest request
    ) {
        rangeChecker.validateRange(request.latitude(), request.longitude(), 0.0, 0.0);
        return buskingService.create(userId, dto(request));
    }

    /**
     * Result vo to response.
     *
     * @param vo List of busking in map vo
     * @return Response of searching the list of busking in specific range of map
     */
    private BuskingsInMapResponse dto(final BuskingMapVo vo) {
        return new BuskingsInMapResponse(vo.id(), vo.latitude(), vo.longitude());
    }

    /**
     * Request to Creation Vo.
     *
     * @param request request
     * @return Vo for busking creation
     */
    private CreateBuskingVo dto(final BuskingRequest request) {
        return new CreateBuskingVo(
            request.title(),
            request.imageFiles(),
            request.latitude(),
            request.longitude(),
            request.keywords(),
            request.description(),
            request.managedStartTime(),
            request.managedEndTime()
        );
    }
}
