package kr.texturized.muus.application;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import kr.texturized.muus.application.service.UserSignUpService;
import kr.texturized.muus.application.service.exception.DuplicatedAccountIdException;
import kr.texturized.muus.application.service.exception.DuplicatedNicknameException;
import kr.texturized.muus.application.service.exception.InvalidAccountException;
import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.entity.UserTypeEnum;
import kr.texturized.muus.domain.vo.SignInResultVo;
import kr.texturized.muus.domain.vo.SignInVo;
import kr.texturized.muus.domain.vo.SignUpResultVo;
import kr.texturized.muus.domain.vo.SignUpVo;
import kr.texturized.muus.infrastructure.repository.UserRepository;
import kr.texturized.muus.test.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
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

    @Test
    void signUpThenSignInInVariableCondition() {
        final SignUpResultVo signUpVo = userSignUpService.signUp(new SignUpVo("redgem92", "wjd1xhd!", "vvVic"));
        assertThat(signUpVo).isNotNull();

        // No such Account
        try {
            userSignUpService.signIn(new SignInVo("redgem91", "asdf"));
            fail();
        } catch (InvalidAccountException e) {

        }
        // Wrong password
        try {
            userSignUpService.signIn(new SignInVo("redgem92", "asdf"));
            fail();
        } catch (InvalidAccountException e) {
        }
        // Sign-in successfully
        final SignInResultVo signInVo = userSignUpService.signIn(new SignInVo("redgem92", "wjd1xhd!"));
        assertThat(signInVo.id()).isEqualTo(signUpVo.id());
    }
}
