package kr.texturized.muus.presentation.api;

import javax.validation.Valid;
import kr.texturized.muus.application.service.SignInOutService;
import kr.texturized.muus.application.service.UserService;
import kr.texturized.muus.common.error.exception.BusinessException;
import kr.texturized.muus.common.error.exception.ErrorCode;
import kr.texturized.muus.common.util.CurrentAccountId;
import kr.texturized.muus.common.util.SignInCheck;
import kr.texturized.muus.common.util.ValidationConstants;
import kr.texturized.muus.domain.entity.UserTypeEnum;
import kr.texturized.muus.domain.vo.AccountVo;
import kr.texturized.muus.domain.vo.SignInVo;
import kr.texturized.muus.domain.vo.SignUpResultVo;
import kr.texturized.muus.domain.vo.SignUpVo;
import kr.texturized.muus.presentation.api.request.SignInRequest;
import kr.texturized.muus.presentation.api.request.SignUpRequest;
import kr.texturized.muus.presentation.api.response.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for User.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SignInOutService signInOutService;

    /**
     * API for validation of account id.
     *
     * @param accountId account id to use
     * @return Available response.
     */
    @GetMapping("/validate/account")
    public ResponseEntity<String> validateAccount(@RequestParam final String accountId) {
        validatePattern(
            accountId,
            ValidationConstants.ACCOUNT_PATTERN,
            ValidationConstants.ACCOUNT_PATTERN_INVALID_MESSAGE
        );
        userService.checkDuplicatedAccountId(accountId);

        return ResponseEntity.status(HttpStatus.OK).body("사용 가능해요.");
    }

    /**
     * API for validation of password.
     *
     * @param password to use
     * @return Available response.
     */
    @GetMapping("/validate/password")
    public ResponseEntity<String> validatePassword(@RequestParam final String password) {
        validatePattern(
            password,
            ValidationConstants.PASSWORD_PATTERN,
            ValidationConstants.PASSWORD_PATTERN_INVALID_MESSAGE
        );

        return ResponseEntity.status(HttpStatus.OK).body("사용 가능해요.");
    }

    /**
     * API for validation of nickname.
     *
     * @param nickname to use, it validates using bean validation.
     * @return Available response.
     */
    @GetMapping("/validate/nickname")
    public ResponseEntity<String> validateNickname(@RequestParam final String nickname) {
        validatePattern(
            nickname,
            ValidationConstants.NICKNAME_PATTERN,
            ValidationConstants.NICKNAME_PATTERN_INVALID_MESSAGE
        );
        userService.checkDuplicatedNickname(nickname);

        return ResponseEntity.status(HttpStatus.OK).body("사용 가능해요.");
    }

    /**
     * Sign-up.
     *
     * @param request request including information for sign-up
     * @return DB identical id of signed-up account
     */
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid final SignUpRequest request) {
        final SignUpVo dto = request.toDto();
        final SignUpResultVo vo = userService.signUp(dto);
        return ResponseEntity.status(HttpStatus.OK).body("Sign-up");
    }

    /**
     * Sign-in.
     * NOTE: No validation for login.
     * Some account id and password may not sign-in
     * because of the change of account and password policy.
     * Let it log-in just checking information in DB.
     *
     * @param request request including information for sign-in
     * @return DB identical id of signed-in account
     */
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody final SignInRequest request) {
        final SignInVo dto = request.toDto();
        final AccountVo vo = userService.getAccount(dto);
        final String result = signInOutService.signIn(vo.accountId());
        return ResponseEntity.status(HttpStatus.OK).body(new SignInResponse(result, vo.userType()));
    }

    @PostMapping("/sign-out")
    public ResponseEntity<String> signOut() {
        signInOutService.signOut();
        return ResponseEntity.status(HttpStatus.OK).body("Sign-out");
    }

    @PatchMapping("/change/password")
    @SignInCheck(userType = {UserTypeEnum.USER, UserTypeEnum.ADMIN})
    public ResponseEntity<String> changeAccountPassword(
        @CurrentAccountId final String accountId,
        final String password
    ) {
        validatePattern(
            password,
            ValidationConstants.PASSWORD_PATTERN,
            ValidationConstants.PASSWORD_PATTERN_INVALID_MESSAGE
        );

        userService.changePassword(accountId, password);

        return ResponseEntity.status(HttpStatus.OK).body("Password changed");
    }

    @PatchMapping("/change/nickname")
    @SignInCheck(userType = {UserTypeEnum.USER, UserTypeEnum.ADMIN})
    public ResponseEntity<String> changeAccountNickname(
        @CurrentAccountId final String accountId,
        final String nickname
    ) {
        validatePattern(
            nickname,
            ValidationConstants.NICKNAME_PATTERN,
            ValidationConstants.NICKNAME_PATTERN_INVALID_MESSAGE
        );

        userService.changeNickname(accountId, nickname);

        return ResponseEntity.status(HttpStatus.OK).body("Password changed");
    }

    private void validatePattern(final String value, final String pattern, final String invalidMessage) {
        if (null == value || !value.matches(pattern)) {
            throw new BusinessException(invalidMessage, ErrorCode.INVALID_INPUT_VALUE);
        }
    }
}
