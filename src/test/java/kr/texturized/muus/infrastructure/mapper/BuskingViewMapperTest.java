package kr.texturized.muus.infrastructure.mapper;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import kr.texturized.muus.domain.entity.Busking;
import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.entity.UserTypeEnum;
import kr.texturized.muus.domain.vo.BuskingMapVo;
import kr.texturized.muus.infrastructure.repository.BuskingRepository;
import kr.texturized.muus.infrastructure.repository.UserRepository;
import kr.texturized.muus.test.IntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class BuskingViewMapperTest extends IntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuskingRepository buskingRepository;

    @Autowired
    private BuskingViewMapper buskingViewMapper;

    @BeforeEach
    void beforeEach() {
        User saveUser = userRepository.save(User.builder()
                .accountId("redgem92")
                .password("asdfqwerzxcv")
                .nickname("HoneyFist")
                //.email("redgem92@gmail.com")
                .userType(UserTypeEnum.USER)
            .build());

        saveBusking(
            saveUser,
            27.0, 120.0,
            LocalDateTime.of(2023, Month.DECEMBER, 25, 12, 0),
            LocalDateTime.of(2023, Month.DECEMBER, 25, 13, 0)
        );
        saveBusking(
            saveUser,
            27.5, 120.5,
            LocalDateTime.of(2023, Month.DECEMBER, 31, 12, 0),
            LocalDateTime.of(2023, Month.DECEMBER, 31, 13, 0)
        );
    }

    Busking saveBusking(
        User user,
        double latitude, double longitude,
        LocalDateTime start, LocalDateTime end
    ) {
        return buskingRepository.save(Busking.builder()
                .host(user)
                .title("Test")
                .description("Test")
                .latitude(latitude)
                .longitude(longitude)
                .managedStartTime(start)
                .managedEndTime(end)
            .build());
    }

    @Test
    void getActiveBuskingInMap() {
        List<BuskingMapVo> buskings = buskingViewMapper.getActiveBuskingsInMap(
            27.0,
            120.0,
            1.0,
            1.0
        );
        assertThat(buskings.size()).isEqualTo(2);

        buskings = buskingViewMapper.getActiveBuskingsInMap(
            27.0,
            120.0,
            0.5,
            0.5
        );
        assertThat(buskings.size()).isEqualTo(1);
    }
}