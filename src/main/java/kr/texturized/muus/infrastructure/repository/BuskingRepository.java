package kr.texturized.muus.infrastructure.repository;

import kr.texturized.muus.domain.entity.Busking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Simple CUD JPA Data for busking.
 */
public interface BuskingRepository extends JpaRepository<Busking, Long> {

}
