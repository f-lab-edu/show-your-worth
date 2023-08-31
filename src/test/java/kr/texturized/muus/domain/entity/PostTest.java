package kr.texturized.muus.domain.entity;

import java.time.LocalDateTime;
import java.time.Month;
import kr.texturized.muus.infrastructure.repository.BuskingRepository;
import kr.texturized.muus.infrastructure.repository.UserRepository;
import kr.texturized.muus.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This test is for checking logics for JPA with sub-class entity.
 * Leave it as a test in local development(Test annotation will be annotated).
 */
public class PostTest /*extends IntegrationTest*/ {

    //@Autowired
    private UserRepository userRepository;
    //@Autowired
    private BuskingRepository buskingRepository;

    /*@Test*/
    void inheritanceTests() {

        User savedUser = userRepository.save(User.builder()
                .accountId("redgem92")
                .password("asdfqwerzxcv")
                .nickname("HoneyFist")
                .email("redgem92@gmail.com")
                .userType(UserType.USER)
            .build());

        Busking busking = buskingRepository.save(Busking.builder()
            .host(savedUser)
            .title("Test")
            .description("Hello fells")
            .latitude(27.0)
            .longitude(120.0)
            .managedStartTime(LocalDateTime.of(2023, Month.DECEMBER, 25, 12, 0))
            .managedEndTime(LocalDateTime.of(2023, Month.DECEMBER, 25, 13, 0))
            .build());

        System.out.println("busking = " + busking);
    }
}
