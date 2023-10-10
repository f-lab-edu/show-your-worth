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
 * Composite-id class for keyword.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ImageId {

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

}
