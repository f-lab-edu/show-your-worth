package kr.texturized.muus.domain.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import kr.texturized.muus.domain.entity.fk.KeywordFk;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity class for keyword.
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Keyword {

    @EmbeddedId
    private KeywordFk id;

    @NotBlank
    @Column(name = "keyword", length = 15)
    private String keyword;

    @Builder
    public Keyword(
        final KeywordFk id,
        final String keyword) {
        this.id = id;
        this.keyword = keyword;
    }
}
