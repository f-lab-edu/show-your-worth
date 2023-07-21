package com.texturized.muus.domain.user.controller;

import com.texturized.muus.common.response.Existence;
import com.texturized.muus.domain.user.dao.UserFindDao;
import com.texturized.muus.domain.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFindDao userFindDao;

    @GetMapping("/{id}")
    public UserResponse getMember(@PathVariable long id) {
        return new UserResponse(userFindDao.findById(id));
    }

}
