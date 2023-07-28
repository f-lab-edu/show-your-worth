package com.texturized.muus.domain.user.dao;

import com.texturized.muus.domain.user.domain.User;
import com.texturized.muus.domain.user.exception.UserNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserFindDao {

    private final UserRepository userRepository;
    private final UserViewMapper userViewMapper;

    public Optional<User> findById(final Long id) {
        final Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }

}
