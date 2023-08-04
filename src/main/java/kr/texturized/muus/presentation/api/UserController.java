package kr.texturized.muus.presentation.api;

import kr.texturized.muus.application.service.UserService;
import kr.texturized.muus.application.service.UserViewService;
import kr.texturized.muus.presentation.api.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userFindDao;
    private final UserViewService userViewFindDao;

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable long id) {
        return new UserResponse(userFindDao.findById(id).get());
    }

    @GetMapping("")
    public void getAllUsers() {
        System.out.println("Users : " + userViewFindDao.getList().toString());
    }

}
