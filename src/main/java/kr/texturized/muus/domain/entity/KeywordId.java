package kr.texturized.muus.domain.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

/**
 * Composite-id class for Keyword.
 */
@Getter
public class KeywordId implements Serializable {

    @NotBlank
    @Column(name = "post_id")
    private final Long postId;

    @NotBlank
    @Column(name = "category_id")
    private final PostCategory category;

    @Builder
    public KeywordId(final Long postId, final PostCategory category) {
        this.postId = postId;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof KeywordId id) {
            return (this.postId.compareTo(id.postId) == 0
                && this.category.compareTo(id.category) == 0);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, category);
    }
}
