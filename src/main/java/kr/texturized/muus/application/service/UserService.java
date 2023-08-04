package kr.texturized.muus.application.service;

import kr.texturized.muus.domain.entity.User;
import kr.texturized.muus.domain.exception.UserNotFoundException;
import java.util.Optional;
import kr.texturized.muus.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findById(final Long id) {
        return Optional.of(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }
}
