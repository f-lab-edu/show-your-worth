package kr.texturized.muus.infrastructure.repository;

import kr.texturized.muus.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository for Image.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

}
