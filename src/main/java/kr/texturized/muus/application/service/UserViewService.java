package kr.texturized.muus.application.service;

import javax.transaction.Transactional;
import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.infrastructure.mapper.UserViewMapper;
import kr.texturized.muus.presentation.api.response.UserResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserViewService {

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
