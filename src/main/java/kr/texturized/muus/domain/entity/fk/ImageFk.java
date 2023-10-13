package kr.texturized.muus.domain.entity.fk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.validation.constraints.NotBlank;
import kr.texturized.muus.domain.entity.PostCategory;
import kr.texturized.muus.infrastructure.repository.converter.type.PostCategoryConverter;
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
public class ImageFk implements Serializable {

    @NotBlank
    private Long postId;

    @NotBlank
    @Convert(converter = PostCategoryConverter.class)
    @Column(name = "category", nullable = false, updatable = false)
    private PostCategory category;

    @NotBlank
    private Integer uploadOrder;

    @Builder
    public ImageFk(
        final Long postId,
        final PostCategory category,
        final Integer uploadOrder) {
        this.postId = postId;
        this.category = category;
        this.uploadOrder = uploadOrder;
    }

}
