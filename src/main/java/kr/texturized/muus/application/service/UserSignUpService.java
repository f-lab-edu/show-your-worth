package kr.texturized.muus.application.service;

import java.util.Optional;
import kr.texturized.muus.application.service.exception.DuplicatedAccountIdException;
import kr.texturized.muus.application.service.exception.DuplicatedNicknameException;
import kr.texturized.muus.application.service.exception.InvalidAccountException;
import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.entity.UserTypeEnum;
import kr.texturized.muus.domain.vo.SignInResultVo;
import kr.texturized.muus.domain.vo.SignInVo;
import kr.texturized.muus.domain.vo.SignUpResultVo;
import kr.texturized.muus.domain.vo.SignUpVo;
import kr.texturized.muus.infrastructure.mapper.UserViewMapper;
import kr.texturized.muus.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for User Sign up.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserViewMapper userViewMapper;

    /**
     * Sign up logic with total validation.
     *
     * @param vo Vo for sign-up
     * @return user with {@code Optional<T>} wrapper class,
     *         Optional is recommended to use for return result
     */
    @Transactional
    public SignUpResultVo signUp(final SignUpVo vo) {
        checkDuplicatedAccountId(vo.accountId());
        checkDuplicatedNickname(vo.nickname());

        final String encodedPassword = passwordEncoder.encode(vo.password());
        final User signUpUser = Optional.of(userRepository.save(User.builder()
                .accountId(vo.accountId())
                .password(encodedPassword)
                .nickname(vo.nickname())
                .userType(UserTypeEnum.USER)
                .build()))
            .map(user -> {
                log.info("Sign up: {}", user);
                return user;
            }).orElseThrow(InvalidAccountException::new);

        return new SignUpResultVo(signUpUser.getId());
    }

    /**
     * Sign-in logic.<br>
     * <br>
     * NOTE: @Transactional(readOnly = true) is effective for selecting a lot of data.
     * Simple selection such as sign-in is not good for it.
     * It causes the overhead in transaction management.
     *
     * @param vo Vo for sign-in
     * @return user with {@code Optional<T>} wrapper class,
     *      Optional is recommended to use for return result
     */
    public SignInResultVo signIn(final SignInVo vo) {
        final User signInUser = userViewMapper.findByAccountId(vo.accountId())
            .filter(user -> passwordEncoder.matches(vo.password(), user.getPassword()))
            .map(user -> {
                log.info("Sign in: {}", user);
                return user;
            }).orElseThrow(InvalidAccountException::new);
        return new SignInResultVo(signInUser.getId());
    }

    /**
     * Check whether account id is duplicated.
     *
     * @param accountId account to user
     */
    public void checkDuplicatedAccountId(String accountId) {
        if (userViewMapper.existsByAccountId(accountId)) {
            throw new DuplicatedAccountIdException();
        }
    }

    /**
     * Check whether nickname is duplicated.
     *
     * @param nickname nickname to use
     */
    public void checkDuplicatedNickname(String nickname) {
        if (userViewMapper.existsByNickname(nickname)) {
            throw new DuplicatedNicknameException();
        }
    }
}
