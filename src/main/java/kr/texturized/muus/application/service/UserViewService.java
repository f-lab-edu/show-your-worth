package kr.texturized.muus.application.service;

import kr.texturized.muus.infrastructure.mapper.UserViewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserViewService {

    private final UserViewMapper userViewMapper;


}
