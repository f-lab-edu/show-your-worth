package kr.texturized.muus.domain.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Composite-id class for keyword.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageId implements Serializable {

    @NotBlank
    @Column(name = "post_id")
    private Long postId;

    @NotBlank
    @Column(name = "category_id")
    private PostCategory category;

    @NotBlank
    @Column(name = "upload_order")
    private Integer uploadOrder;

    @Builder
    public ImageId(
        final Long postId,
        final PostCategory category,
        final Integer uploadOrder) {
        this.postId = postId;
        this.category = category;
        this.uploadOrder = uploadOrder;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof ImageId id) {
            return (this.postId.compareTo(id.postId) == 0
                && this.category.compareTo(id.category) == 0
                && this.uploadOrder.compareTo(id.uploadOrder) == 0);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, category, uploadOrder);
    }
}
