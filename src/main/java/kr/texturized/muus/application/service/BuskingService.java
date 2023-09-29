package kr.texturized.muus.application.service;

import java.util.List;
import kr.texturized.muus.common.storage.PostImageStorage;
import kr.texturized.muus.dao.BuskingDao;
import kr.texturized.muus.domain.vo.BuskingVo;
import kr.texturized.muus.domain.vo.CreateBuskingVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for Busking Create, Update and Delete.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BuskingService {

    private final BuskingDao buskingDao;
    private final PostImageStorage postImageStorage;

    /**
     * Create the busking.
     *
     * @param userId Host ID of busking in DB
     * @param vo VO for busking creation.
     * @return Created busking ID
     */
    @Transactional
    public Long create(final Long userId, final CreateBuskingVo vo) {
        return buskingDao.create(userId, dto(vo.imageFiles().stream()
                .map(image -> {
                    // TODO: Save image in Image Storage, then extract relative path for images
                    log.info("Image {} is saved in {}", image.getName(), image.getName());
                    return image.getName();
                })
                .toList(),
            vo));
    }

    private BuskingVo dto(final List<String> imagePaths, final CreateBuskingVo vo) {
        return new BuskingVo(
            vo.title(),
            imagePaths,
            vo.latitude(),
            vo.longitude(),
            vo.keywords(),
            vo.description(),
            vo.managedStartTime(),
            vo.managedEndTime()
        );
    }

}
