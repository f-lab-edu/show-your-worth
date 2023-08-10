package kr.texturized.muus.presentation.api;

import javax.validation.Valid;
import kr.texturized.muus.application.service.UserService;
import kr.texturized.muus.application.service.UserViewService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
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
    public String signUp(
        @RequestBody @Valid final AccountRequest accountRequest,
        @RequestBody @Valid final PasswordRequest passwordRequest,
        @RequestBody @Valid final NicknameRequest nicknameRequest,
        @RequestBody @Valid final EmailRequest emailRequest
        ) {
        return "Created";
    }


    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable long id) {
        return new UserResponse(userService.findById(id).get());
    }

    @GetMapping("")
    public void getAllUsers() {
        System.out.println("Users : " + userViewService.getList().toString());
    }

}
