package kr.texturized.muus.dao;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import kr.texturized.muus.domain.exception.UserNotFoundException;
import kr.texturized.muus.domain.vo.BuskingVo;
import kr.texturized.muus.test.IntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class BuskingDaoTest extends IntegrationTest {

    @Autowired
    private BuskingDao buskingDao;

    private BuskingVo vo = new BuskingVo(
        "Title",
        List.of(
            "image/1",
            "image/2",
            "image/3",
            "image/4"
        ),
        45.0,
        127.0,
        List.of(
            "hello",
            "this",
            "is",
            "just test"
        ),
        "Description",
        LocalDateTime.of(2023, Month.DECEMBER, 25, 12, 0),
        LocalDateTime.of(2023, Month.DECEMBER, 25, 13, 0)
    );
    @Test
    void wrongUserIdThenCreateFailed() {
        Assertions.assertThrows(UserNotFoundException.class, () ->{
            buskingDao.create(0L, vo);
        });
    }

    @Test
    void createBusking() {
        Long buskingId = buskingDao.create(1L, vo);
        log.info("buskingId: {}", buskingId);
    }
}