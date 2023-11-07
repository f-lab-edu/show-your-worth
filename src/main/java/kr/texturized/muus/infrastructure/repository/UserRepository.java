package kr.texturized.muus.infrastructure.repository;

import io.hypersistence.utils.spring.repository.HibernateRepository;
import kr.texturized.muus.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends HibernateRepository<User>, JpaRepository<User, Long> {
}
