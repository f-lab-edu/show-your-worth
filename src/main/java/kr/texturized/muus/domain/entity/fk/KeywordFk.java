package kr.texturized.muus.domain.entity.fk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.validation.constraints.NotBlank;
import kr.texturized.muus.domain.entity.PostCategoryEnum;
import kr.texturized.muus.infrastructure.repository.converter.type.PostCategoryConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Composite-id class for Keyword.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class KeywordFk implements Serializable {

    @NotBlank
    @Column(name = "post_id")
    private Long postId;

    @NotBlank
    @Convert(converter = PostCategoryConverter.class)
    @Column(name = "category", nullable = false, updatable = false)
    private PostCategoryEnum category;

    @Builder
    public KeywordFk(final Long postId, final PostCategoryEnum category) {
        this.postId = postId;
        this.category = category;
    }
}
