package kr.texturized.muus.infrastructure.repository;

import kr.texturized.muus.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
