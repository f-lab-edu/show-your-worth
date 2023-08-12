package kr.texturized.muus.presentation.api;

import javax.validation.Valid;
import kr.texturized.muus.application.service.UserSignUpService;
import kr.texturized.muus.application.service.exception.InvalidAccountException;
import kr.texturized.muus.presentation.api.request.AccountRequest;
import kr.texturized.muus.presentation.api.request.EmailRequest;
import kr.texturized.muus.presentation.api.request.NicknameRequest;
import kr.texturized.muus.presentation.api.request.PasswordRequest;
import kr.texturized.muus.presentation.api.response.AccountResponse;
import kr.texturized.muus.presentation.api.response.NicknameResponse;
import kr.texturized.muus.presentation.api.response.PasswordResponse;
import kr.texturized.muus.presentation.api.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
     * @param request for account id, it validates using bean validation.
     * @return Available response.
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/validate/account")
    public AccountResponse validateAccount(@RequestBody @Valid final AccountRequest request) {
        return new AccountResponse("사용 가능해요.");
    }

    /**
     * API for validation of password.
     *
     * @param request for password, it validates using bean validation.
     * @return Available response.
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/validate/password")
    public PasswordResponse validatePassword(@RequestBody @Valid final PasswordRequest request) {
        return new PasswordResponse("사용 가능해요.");
    }

    /**
     * API for validation of nickname.
     *
     * @param request for nickname, it validates using bean validation.
     * @return Available response.
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/validate/nickname")
    public NicknameResponse validateNickname(@RequestBody @Valid final NicknameRequest request) {
        return new NicknameResponse("사용 가능해요.");
    }

    /**
     * API for validation of email.
     *
     * @param request for email, it validates using bean validation.
     * @return Available response.
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/validate/email")
    public String validateEmail(@RequestBody @Valid final EmailRequest request) {
        // TODO: it requires implementation of sign up with authentication
        return "Accepted";
    }


    /**
     * sign-up.
     *
     * @param accountRequest account id.
     * @param passwordRequest password.
     * @param nicknameRequest nickname.
     * @param emailRequest email.
     * @return User response with sign-up information.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public UserResponse signUp(
        @RequestBody @Valid final AccountRequest accountRequest,
        @RequestBody @Valid final PasswordRequest passwordRequest,
        @RequestBody @Valid final NicknameRequest nicknameRequest,
        @RequestBody @Valid final EmailRequest emailRequest) {

        return new UserResponse(userSignUpService.signUp(
            accountRequest.accountId(),
            passwordRequest.password(),
            nicknameRequest.nickname(),
            emailRequest.email()
        ).orElseThrow(InvalidAccountException::new));
    }

    /**
     * sign-in.
     *
     * @param accountRequest account id.
     * @param passwordRequest password.
     * @return User response with sign-in information.
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/sign-in")
    public UserResponse signIn(
        @RequestBody final AccountRequest accountRequest,
        @RequestBody final PasswordRequest passwordRequest
    ) {
        return new UserResponse(userSignUpService.signIn(
            accountRequest.accountId(),
            passwordRequest.password()
        ).orElseThrow(InvalidAccountException::new));
    }

}
