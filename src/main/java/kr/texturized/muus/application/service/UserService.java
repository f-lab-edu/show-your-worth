package kr.texturized.muus.application.service;

import java.util.Optional;
import kr.texturized.muus.application.service.exception.DuplicatedAccountIdException;
import kr.texturized.muus.application.service.exception.DuplicatedNicknameException;
import kr.texturized.muus.application.service.exception.InvalidAccountException;
import kr.texturized.muus.common.util.PasswordEncryptor;
import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.entity.UserTypeEnum;
import kr.texturized.muus.domain.vo.AccountVo;
import kr.texturized.muus.domain.vo.SignInVo;
import kr.texturized.muus.domain.vo.SignUpResultVo;
import kr.texturized.muus.domain.vo.SignUpVo;
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
public class UserService {

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

        final String encodedPassword = PasswordEncryptor.encrypt(vo.password());
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
     * Get account information matching with account id and password.<br>
     * <br>
     * NOTE: @Transactional(readOnly = true) is effective for selecting a lot of data.
     * Simple selection such as sign-in is not good for it.
     * It causes the overhead in transaction management.
     *
     * @param vo Vo for account validation
     * @return user with {@code Optional<T>} wrapper class,
     *      Optional is recommended to use for return result
     */
    public AccountVo getAccount(final SignInVo vo) {
        final User signInUser = getUser(vo.accountId())
            .filter(user -> PasswordEncryptor.matches(vo.password(), user.getPassword()))
            .orElseThrow(InvalidAccountException::new);
        return new AccountVo(signInUser.getAccountId(), signInUser.getUserType());
    }

    /**
     * Check Account ID and password matched.
     *
     * @param accountId Account ID to check
     * @param password Current password
     */
    public void passwordMatches(final String accountId, final String password) {
        getUser(accountId)
            .filter(user -> PasswordEncryptor.matches(password, user.getPassword()))
            .orElseThrow(InvalidAccountException::new);
    }

    /**
     * Change password.
     *
     * @param accountId Account ID to change
     * @param password Password to change
     */
    @Transactional
    public void changePassword(
        final String accountId,
        final String password
    ) {
        final User user = getUser(accountId).orElseThrow(InvalidAccountException::new);
        user.update(
            password,
            user.getNickname(),
            user.getProfileImagePath()
        );
        userRepository.save(user);
    }

    /**
     * Change nickname.
     *
     * @param accountId Account ID to change
     * @param nickname Nickname to change
     */
    @Transactional
    public void changeNickname(
        final String accountId,
        final String nickname
    ) {
        final User user = getUser(accountId).orElseThrow(InvalidAccountException::new);
        user.update(
            user.getPassword(),
            nickname,
            user.getProfileImagePath()
        );
        userRepository.save(user);
    }

    /**
     * Get User Entity from DB.
     *
     * @param accountId Account ID
     * @return User Entity
     */
    private Optional<User> getUser(final String accountId) {
        return userViewMapper.findByAccountId(accountId);
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

    /**
     * Get user type for account id.
     *
     * @param accountId Account ID to find user type
     * @return User Type
     */
    public UserTypeEnum getAccountIdUserType(String accountId) {
        return userViewMapper.findUserTypeByAccountId(accountId);
    }
}
