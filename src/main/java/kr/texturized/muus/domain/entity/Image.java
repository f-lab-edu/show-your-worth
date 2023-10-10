package kr.texturized.muus.domain.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity for image.
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {

    @EmbeddedId
    private ImageId id;

    @NotBlank
    @Column(name = "path", length = 250)
    private String path;       // Relative image path


    @Builder
    public Image(
        final ImageId id,
        final String path
    ) {
        this.id = id;
        this.path = path;
    }
}
