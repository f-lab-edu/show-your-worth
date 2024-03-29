package kr.texturized.muus.dao;

import kr.texturized.muus.domain.entity.Busking;
import kr.texturized.muus.domain.entity.Image;
import kr.texturized.muus.domain.entity.Keyword;
import kr.texturized.muus.domain.entity.PostCategoryEnum;
import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.entity.fk.ImageFk;
import kr.texturized.muus.domain.entity.fk.KeywordFk;
import kr.texturized.muus.domain.exception.UserNotFoundException;
import kr.texturized.muus.domain.vo.BuskingVo;
import kr.texturized.muus.infrastructure.mapper.UserViewMapper;
import kr.texturized.muus.infrastructure.repository.BuskingRepository;
import kr.texturized.muus.infrastructure.repository.ImageRepository;
import kr.texturized.muus.infrastructure.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * DAO for Busking CUD.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BuskingDao {

    private final UserViewMapper userViewMapper;
    private final BuskingRepository buskingRepository;
    private final KeywordRepository keywordRepository;
    private final ImageRepository imageRepository;

    /**
     * Create busking.
     *
     * @param userId User ID in DB creating busking
     * @param vo VO for busking creation
     * @return busking ID
     */
    public Long create(final Long userId, final BuskingVo vo) {
        User user = userViewMapper.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));

        // TODO : Refactor, split logic using private method

        Busking busking = buskingRepository.save(Busking.builder()
                .host(user)
                .title(vo.title())
                .description(vo.description())
                .latitude(vo.latitude())
                .longitude(vo.longitude())
                .managedStartTime(vo.managedStartTime())
                .managedEndTime(vo.managedEndTime())
            .build());

        log.info("Busking is created({}): {}", busking.getId(), busking.getTitle());

        vo.keywords()
            .forEach(keyword -> {
                Keyword entity = keywordRepository.save(Keyword.builder()
                        .id(new KeywordFk(busking.getId(), PostCategoryEnum.BUSKING))
                        .keyword(keyword)
                    .build());

                log.info("Keyword: {} for Busking {} is added", entity.getKeyword(), busking.getTitle());
            });

        for (int order = 0; order < vo.imagePaths().size(); ++order) {
            Image entity = imageRepository.save(Image.builder()
                    .id(ImageFk.builder()
                        .postId(busking.getId())
                        .postType(PostCategoryEnum.BUSKING)
                        .uploadOrder(order)
                        .build())
                    .path(vo.imagePaths().get(order))
                .build());

            log.info("Image No. {} for Busking {} is added in {}", order, busking.getTitle(), entity.getPath());
        }

        return busking.getId();
    }

}
