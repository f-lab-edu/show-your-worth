package kr.texturized.muus.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.fail;

import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.entity.UserTypeEnum;
import kr.texturized.muus.test.IntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserRepositoryTest extends IntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void whenAddDuplicatedAccountIDUserThenReturnError() {
        try {
            User user1 = User.builder()
                .accountId("Overlap")
                .password("asdf")
                .nickname("Unoverlap")
                .email("unoverlapMail@naver.com")
                .userType(UserTypeEnum.USER)
                .profileImagePath("")
                .build();
            userRepository.save(user1);

            User user2 = User.builder()
                .accountId("Overlap")
                .password("asdf")
                .nickname("Unoverlap1")
                .email("unoverlapMail1@naver.com")
                .profileImagePath("")
                .userType(UserTypeEnum.USER)
                .build();
            userRepository.save(user2);
            fail();
        } catch (Exception e) {
        }
    }
    @Test
    void whenAddDuplicatedNicknameUserThenReturnError() {
        try {
            User user1 = User.builder()
                .accountId("unoverlap1")
                .password("asdf")
                .nickname("overlap")
                .email("unoverlapMail@naver.com")
                .userType(UserTypeEnum.USER)
                .profileImagePath("")
                .build();
            userRepository.save(user1);

            User user2 = User.builder()
                .accountId("unoverlap2")
                .password("asdf")
                .nickname("overlap")
                .email("unoverlapMail1@naver.com")
                .profileImagePath("")
                .userType(UserTypeEnum.USER)
                .build();
            userRepository.save(user2);
            fail();
        } catch (Exception e) {
        }
    }
    @Test
    void whenAddDuplicatedEmailUserThenReturnError() {
        try {
            User user1 = User.builder()
                .accountId("unoverlapUser1")
                .password("asdf")
                .nickname("unoverlap1")
                .email("overlapMail@naver.com")
                .userType(UserTypeEnum.USER)
                .profileImagePath("")
                .build();
            userRepository.save(user1);

            User user2 = User.builder()
                .accountId("unoverlap2")
                .password("asdf")
                .nickname("unoverlapNickname2")
                .email("overlapMail@naver.com")
                .profileImagePath("")
                .userType(UserTypeEnum.USER)
                .build();
            userRepository.save(user2);
            fail();
        } catch (Exception e) {
        }
    }
}
