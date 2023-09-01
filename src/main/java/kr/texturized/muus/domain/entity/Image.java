package kr.texturized.muus.domain.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity for image.
 */
@Entity
@Table(
    name = "image",
    uniqueConstraints = {@UniqueConstraint(columnNames = {
        "post_id",
        "category_id",
        "upload_order"
    })}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {

    @EmbeddedId
    private ImageId id;

    @MapsId("post_id")
    @NotBlank
    @ManyToOne
    private Post post;

    @NotBlank
    @Column(name = "path", length = 250, unique = true)
    private String path;       // Relative image path


    @Builder
    public Image(
        final ImageId id,
        final Post post,
        final String path
    ) {
        this.id = id;
        this.post = post;
        this.path = path;
    }
}
