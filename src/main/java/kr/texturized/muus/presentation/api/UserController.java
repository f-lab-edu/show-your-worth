package kr.texturized.muus.presentation.api;

import javax.validation.Valid;
import kr.texturized.muus.application.service.UserSignUpService;
import kr.texturized.muus.common.error.exception.BusinessException;
import kr.texturized.muus.common.error.exception.ErrorCode;
import kr.texturized.muus.common.util.ValidationUtils;
import kr.texturized.muus.domain.vo.SignInResultVo;
import kr.texturized.muus.domain.vo.SignInVo;
import kr.texturized.muus.domain.vo.SignUpResultVo;
import kr.texturized.muus.domain.vo.SignUpVo;
import kr.texturized.muus.presentation.api.request.SignInRequest;
import kr.texturized.muus.presentation.api.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final UserSignUpService userSignUpService;

    /**
     * API for validation of account id.
     *
     * @param accountId account id to use
     * @return Available response.
     */
    @GetMapping("/validate/account")
    public ResponseEntity<String> validateAccount(@RequestParam final String accountId) {
        if (null == accountId || !accountId.matches(ValidationUtils.ACCOUNT_PATTERN)) {
            throw new BusinessException(
                ValidationUtils.ACCOUNT_PATTERN_INVALID_MESSAGE,
                ErrorCode.INVALID_INPUT_VALUE
            );
        }

        userSignUpService.checkDuplicatedAccountId(accountId);

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
        if (null == password || !password.matches(ValidationUtils.PASSWORD_PATTERN)) {
            throw new BusinessException(
                ValidationUtils.PASSWORD_PATTERN_INVALID_MESSAGE,
                ErrorCode.INVALID_INPUT_VALUE
            );
        }

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
        if (null == nickname || !nickname.matches(ValidationUtils.NICKNAME_PATTERN)) {
            throw new BusinessException(
                ValidationUtils.NICKNAME_PATTERN_INVALID_MESSAGE,
                ErrorCode.INVALID_INPUT_VALUE
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body("사용 가능해요.");
    }

    /**
     * Sign-up.
     *
     * @param request request including information for sign-up
     * @return DB identical id of signed-up account
     */
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResultVo> signUp(@RequestBody @Valid final SignUpRequest request) {
        final SignUpVo dto = request.dto();
        final SignUpResultVo vo = userSignUpService.signUp(dto);
        return ResponseEntity.status(HttpStatus.OK).body(vo);
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
    public ResponseEntity<SignInResultVo> signIn(@RequestBody final SignInRequest request) {
        final SignInVo dto = request.dto();
        final SignInResultVo vo = userSignUpService.signIn(dto);
        return ResponseEntity.status(HttpStatus.OK).body(vo);
    }

}
