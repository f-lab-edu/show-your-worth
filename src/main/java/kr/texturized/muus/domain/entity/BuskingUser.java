package kr.texturized.muus.domain.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "busking_user",
    uniqueConstraints = {@UniqueConstraint(columnNames = {
        "busking_id",
        "user_id"
    })}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BuskingUser {
    @EmbeddedId
    BuskingUserId id;

    @MapsId("busking")
    @ManyToOne
    @JoinColumn(name = "busking_id", nullable = false)
    private Busking busking;
}
