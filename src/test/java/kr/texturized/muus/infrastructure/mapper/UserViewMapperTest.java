package kr.texturized.muus.infrastructure.mapper;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.entity.UserTypeEnum;
import kr.texturized.muus.infrastructure.repository.UserRepository;
import kr.texturized.muus.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserViewMapperTest extends IntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserViewMapper userViewMapper;

    @Test
    void signUpThenSignIn() {
        User signUp = userRepository.save(User.builder()
                .accountId("accountId")
                .password("password")
                .nickname("nickname")
                .email("email@f-lab.kr")
                .userType(UserTypeEnum.USER)
            .build());

        Optional<User> signIn = userViewMapper.findByAccountId("accountId");
        assertThat(signIn).isNotEmpty();
        assertThat(signIn.get().getAccountId()).isEqualTo(signUp.getAccountId());
        assertThat(signIn.get().getPassword()).isEqualTo(signUp.getPassword());
        assertThat(signIn.get().getNickname()).isEqualTo(signUp.getNickname());
        assertThat(signIn.get().getUserType()).isEqualTo(signUp.getUserType());
        assertThat(signIn.get().getProfileImage()).isEqualTo(signUp.getProfileImage());
        assertThat(signIn.get().getEmail()).isEqualTo(signUp.getEmail());
    }

}
