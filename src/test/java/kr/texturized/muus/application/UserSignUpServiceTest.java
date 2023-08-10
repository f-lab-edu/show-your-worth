package kr.texturized.muus.application;

import static org.junit.jupiter.api.Assertions.fail;

import kr.texturized.muus.application.service.UserSignUpService;
import kr.texturized.muus.application.service.exception.AlreadySignedUpEmailException;
import kr.texturized.muus.application.service.exception.DuplicatedAccountIdException;
import kr.texturized.muus.application.service.exception.DuplicatedNicknameException;
import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.entity.UserType;
import kr.texturized.muus.infrastructure.repository.UserRepository;
import kr.texturized.muus.test.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserSignUpServiceTest extends IntegrationTest {

    @Autowired
    private UserSignUpService userSignUpService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void initTestUser() {
        userRepository.save(User.builder()
                .accountId("accountId")
                .password("password")
                .nickname("nickname")
                .email("email@f-lab.kr")
                .userType(UserType.USER)
            .build());
    }

    @AfterEach
    void deleteAll() {
        userRepository.deleteAll();
    }

    @Test
    void overlappedAccountIdReturnException() {
        try {
            userSignUpService.validateAccountId("accountId");
            fail();
        } catch (DuplicatedAccountIdException e) {
        }
    }

    @Test
    void overlappedNicknameReturnException() {
        try {
            userSignUpService.validateNickname("nickname");
            fail();
        } catch (DuplicatedNicknameException e) {
        }
    }

    @Test
    void overlappedEmailReturnException() {
        try {
            userSignUpService.validateEmail("email@f-lab.kr");
            fail();
        } catch (AlreadySignedUpEmailException e) {
        }
    }
}
