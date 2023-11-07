package kr.texturized.muus.application;

import static org.junit.jupiter.api.Assertions.fail;

import kr.texturized.muus.application.service.UserService;
import kr.texturized.muus.application.service.exception.DuplicatedAccountIdException;
import kr.texturized.muus.application.service.exception.DuplicatedNicknameException;
import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.entity.UserTypeEnum;
import kr.texturized.muus.infrastructure.repository.UserRepository;
import kr.texturized.muus.test.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserSignUpServiceTest extends IntegrationTest {

    @Autowired
    private UserService userSignUpService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void initTestUser() {
        User user = User.builder()
            .accountId("accountId")
            .password("password")
            .nickname("nickname12")
            .userType(UserTypeEnum.USER)
            .build();
        userRepository.save(user);
    }

    @AfterEach
    void deleteAll() {
        userRepository.deleteAll();
    }

    @Test
    void overlappedAccountIdReturnException() {
        try {
            userSignUpService.checkDuplicatedAccountId("accountId");
            fail();
        } catch (DuplicatedAccountIdException e) {
        }
    }

    @Test
    void overlappedNicknameReturnException() {
        try {
            userSignUpService.checkDuplicatedNickname("nickname12");
            fail();
        } catch (DuplicatedNicknameException e) {
        }
    }
}
