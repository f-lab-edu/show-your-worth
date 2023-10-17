package kr.texturized.muus.infrastructure.repository;

import kr.texturized.muus.domain.entity.Keyword;
import kr.texturized.muus.domain.entity.fk.KeywordFk;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for Keyword.
 */
public interface KeywordRepository extends JpaRepository<Keyword, KeywordFk> {

}
