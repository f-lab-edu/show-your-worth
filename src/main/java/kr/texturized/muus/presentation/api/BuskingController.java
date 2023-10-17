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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * Create Busking Post.<br>
     * <br>
     * There are several reasons why imageFiles are seperated with dto.<br>
     * 1. Normally, client has the responsibility for uploading image to storage using ajax.
     * In this case, I need experience for uploading images in server. So I made logics for it.
     * It may be deprecated when some webpages are made. So I split for this changes.<br>
     * <br>
     * 2. There is an another way to upload, base64 encoding. But it's ineffective way because
     * encoded images strings are too long, so it's hard to request.<br>
     * <br>
     * Reference: <a href="https://www.inflearn.com/questions/307133/image-%EC%A0%84%EC%86%A1%EA%B3%BC-%ED%95%A8%EA%BB%98-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%8A%94-json%EC%9C%BC%EB%A1%9C-%EB%B3%B4%EB%82%B4%EA%B3%A0-%EC%8B%B6%EC%9D%80-%EA%B2%BD%EC%9A%B0">image 전송과 함께 데이터는 json으로 보내고 싶은 경우</a>
     *
     * @param userId User's ID in DB
     * @param request DTO for busking post information
     * @param imageFiles images for post
     * @return Created busking ID in DB
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public Long createBusking(
        @PathVariable(value = "id") final Long userId,
        @Valid @RequestPart final BuskingRequest request,
        @RequestPart("images") final MultipartFile[] imageFiles
    ) {
        rangeChecker.validateRange(request.latitude(), request.longitude(), 0.0, 0.0);
        return buskingService.create(userId, dto(request, imageFiles));
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
    private CreateBuskingVo dto(final BuskingRequest request,
        final MultipartFile[] imageFiles
    ) {
        return new CreateBuskingVo(
            request.title(),
            List.of(imageFiles),
            request.latitude(),
            request.longitude(),
            request.keywords(),
            request.description(),
            request.managedStartTime(),
            request.managedEndTime()
        );
    }
}
