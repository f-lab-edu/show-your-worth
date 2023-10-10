package kr.texturized.muus.domain.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
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
public class KeywordId implements Serializable {

    @NotBlank
    @Column(name = "post_id")
    private Long postId;

    @NotBlank
    @Column(name = "category_id")
    private PostCategory category;

    @Builder
    public KeywordId(final Long postId, final PostCategory category) {
        this.postId = postId;
        this.category = category;
    }
}
