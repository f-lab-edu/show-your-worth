package kr.texturized.muus.infrastrure.repository;

import static org.junit.jupiter.api.Assertions.fail;

import kr.texturized.muus.domain.entity.AccountID;
import kr.texturized.muus.domain.entity.Email;
import kr.texturized.muus.domain.entity.Nickname;
import kr.texturized.muus.domain.entity.Password;
import kr.texturized.muus.domain.entity.ProfileImage;
import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.entity.UserType;
import kr.texturized.muus.infrastructure.repository.UserRepository;
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
                .accountID(new AccountID("Overlap"))
                .password(new Password("asdf"))
                .nickname(new Nickname("Unoverlap"))
                .email(new Email("unoverlapMail@naver.com"))
                .userType(UserType.USER)
                .profileImage(new ProfileImage(""))
                .build();
            userRepository.save(user1);

            User user2 = User.builder()
                .accountID(new AccountID("Overlap"))
                .password(new Password("asdf"))
                .nickname(new Nickname("Unoverlap1"))
                .email(new Email("unoverlapMail1@naver.com"))
                .profileImage(new ProfileImage(""))
                .userType(UserType.USER)
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
                .accountID(new AccountID("unoverlap1"))
                .password(new Password("asdf"))
                .nickname(new Nickname("overlap"))
                .email(new Email("unoverlapMail@naver.com"))
                .userType(UserType.USER)
                .profileImage(new ProfileImage(""))
                .build();
            userRepository.save(user1);

            User user2 = User.builder()
                .accountID(new AccountID("unoverlap2"))
                .password(new Password("asdf"))
                .nickname(new Nickname("overlap"))
                .email(new Email("unoverlapMail1@naver.com"))
                .profileImage(new ProfileImage(""))
                .userType(UserType.USER)
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
                .accountID(new AccountID("unoverlapUser1"))
                .password(new Password("asdf"))
                .nickname(new Nickname("unoverlap1"))
                .email(new Email("overlapMail@naver.com"))
                .userType(UserType.USER)
                .profileImage(new ProfileImage(""))
                .build();
            userRepository.save(user1);

            User user2 = User.builder()
                .accountID(new AccountID("unoverlap2"))
                .password(new Password("asdf"))
                .nickname(new Nickname("unoverlapNickname2"))
                .email(new Email("overlapMail@naver.com"))
                .profileImage(new ProfileImage(""))
                .userType(UserType.USER)
                .build();
            userRepository.save(user2);
            fail();
        } catch (Exception e) {
        }
    }
}
