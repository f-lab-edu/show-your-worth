package kr.texturized.muus.presentation.api;

import javax.validation.Valid;
import kr.texturized.muus.application.service.UserService;
import kr.texturized.muus.application.service.UserViewService;
import kr.texturized.muus.presentation.api.request.AccountValidRequest;
import kr.texturized.muus.presentation.api.request.EmailValidRequest;
import kr.texturized.muus.presentation.api.request.PasswordValidRequest;
import kr.texturized.muus.presentation.api.response.AccountValidResponse;
import kr.texturized.muus.presentation.api.response.PasswordValidResponse;
import kr.texturized.muus.presentation.api.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public AccountValidResponse validateAccount(@RequestBody @Valid final AccountValidRequest request) {
        return new AccountValidResponse("사용 가능해요.");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/validate/password")
    public PasswordValidResponse validatePassword(@RequestBody @Valid final PasswordValidRequest request) {
        return new PasswordValidResponse("사용 가능해요.");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/validate/email")
    public String validateEmail(@RequestBody @Valid final EmailValidRequest request) {
        return "Accepted";  // TODO: Temporary return 'accepted', it requires implement sign up with authentication
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
