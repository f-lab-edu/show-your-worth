package com.texturized.muus.domain.user.dao;

import com.texturized.muus.domain.user.domain.User;
import com.texturized.muus.domain.user.exception.UserNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserFindDao {

    private final UserRepository userRepository;

    public Optional<User> findById(final Long id) {
        return Optional.of(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }
}
