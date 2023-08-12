package kr.texturized.muus.application.service;

import java.util.Optional;
import kr.texturized.muus.application.service.exception.AlreadySignedUpEmailException;
import kr.texturized.muus.application.service.exception.DuplicatedAccountIdException;
import kr.texturized.muus.application.service.exception.DuplicatedNicknameException;
import kr.texturized.muus.application.service.exception.InvalidAccountException;
import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.entity.UserType;
import kr.texturized.muus.infrastructure.mapper.UserViewMapper;
import kr.texturized.muus.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for User Sign up.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserRepository userRepository;
    private final UserViewMapper userViewMapper;

    /**
     * sign up logic with total validation.
     *
     * @param accountId id to sign up
     * @param password password to sign up
     * @param nickname nickname to use
     * @param email email to sign up
     * @return user with {@code Optional<T>} wrapper class,
     *         Optional is recommended to use for return result
     */
    @Transactional
    public Optional<User> signUp(
        final String accountId,
        final String password,
        final String nickname,
        final String email
    ) {
        validateAccountId(accountId);
        validateNickname(nickname);
        validateEmail(email);

        return Optional.of(userRepository.save(User.builder()
                    .accountId(accountId)
                    .password(password /* TODO: PasswordEncoder */)
                    .nickname(nickname)
                    .email(email)
                    .userType(UserType.USER)
                .build()))
            .map(user -> {
                log.info("Sign up: {}", user);
                return user;
            });
    }

    /**
     * Sign-in logic.
     *
     * @param accountId id to sign in
     * @param password password for id
     * @return user with {@code Optional<T>} wrapper class,
     *         Optional is recommended to use for return result
     */
    @Transactional
    public Optional<User> signIn(final String accountId, final String password) {
        return Optional.ofNullable(userViewMapper.findByAccountId(accountId))
            .filter(user -> user.getPassword().equals(password))
            .map(user -> {
                log.info("Sign in: {}", user);
                return user;
            });
    }

    /**
     * Check whether account id is able to sign up.
     *
     * @param accountId id to use
     */
    public void validateAccountId(String accountId) {
        if (userViewMapper.existsByAccountId(accountId)) {
            throw new DuplicatedAccountIdException();
        }
    }

    /**
     * Check whether nickname is able to use.
     *
     * @param nickname nickname to use
     */
    public void validateNickname(String nickname) {
        if (userViewMapper.existsByNickname(nickname)) {
            throw new DuplicatedNicknameException();
        }
    }

    /**
     * Check whether email is able to sign up.
     *
     * @param email email to sign up
     */
    public void validateEmail(String email) {
        if (userViewMapper.existsByEmail(email)) {
            throw new AlreadySignedUpEmailException();
        }
    }
}
