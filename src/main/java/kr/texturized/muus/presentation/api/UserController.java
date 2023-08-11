package kr.texturized.muus.presentation.api;

import javax.validation.Valid;
import kr.texturized.muus.application.service.UserSignUpService;
import kr.texturized.muus.application.service.UserViewService;
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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserSignUpService userSignUpService;
    private final UserViewService userViewService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/validate/account")
    public AccountResponse validateAccount(@RequestBody @Valid final AccountRequest request) {
        return new AccountResponse("사용 가능해요.");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/validate/password")
    public PasswordResponse validatePassword(@RequestBody @Valid final PasswordRequest request) {
        return new PasswordResponse("사용 가능해요.");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/validate/nickname")
    public NicknameResponse validateNickname(@RequestBody @Valid final NicknameRequest request) {
        return new NicknameResponse("사용 가능해요.");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/validate/email")
    public String validateEmail(@RequestBody @Valid final EmailRequest request) {
        return "Accepted";  // TODO: Temporary return 'accepted', it requires implementation of
                            // sign up with authentication
    }

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
