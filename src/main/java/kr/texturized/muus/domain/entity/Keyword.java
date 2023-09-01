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
 * Entity class for keyword.
 */
@Entity
@Table(
    name = "keyword",
    uniqueConstraints = {@UniqueConstraint(columnNames = {
        "post_id",
        "category_id"
    })}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Keyword {

    @EmbeddedId
    private PostResourceId id;

    @MapsId("post_id")
    @NotBlank
    @ManyToOne
    private Post post;

    @NotBlank
    @Column(name = "keyword", length = 15)
    private String keyword;

    @Builder
    public Keyword(final PostResourceId id, final String keyword) {
        this.id = id;
        this.keyword = keyword;
    }
}
