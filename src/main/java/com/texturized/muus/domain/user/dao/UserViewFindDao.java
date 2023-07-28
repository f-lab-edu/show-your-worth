package com.texturized.muus.domain.user.dao;

import com.texturized.muus.domain.user.domain.User;
import com.texturized.muus.domain.user.dto.UserResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserViewFindDao {

    private final UserViewMapper userViewMapper;

    @Transactional
    public List<UserResponse> getList() {

        Map<String, Object> params = new HashMap<>();

        return userViewMapper
            .getList(params).stream()
            .map(this::dto)
            .collect(Collectors.toList());
    }

    private UserResponse dto(User user) {
        return new UserResponse(user);
    }
}
