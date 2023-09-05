package kr.texturized.muus.domain.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import lombok.Builder;

/**
 * Composite-id class for BuskingUser.
 */
public class BuskingUserId implements Serializable {

    @NotBlank
    @Column(name = "busking_id")
    private final Long buskingId;

    @NotBlank
    @Column(name = "user_id")
    private final Long userId;

    @Builder
    public BuskingUserId(final Long buskingId, final Long userId) {
        this.buskingId = buskingId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof BuskingUserId id) {
            return (this.buskingId.compareTo(id.buskingId) == 0
                && this.userId.compareTo(id.userId) == 0);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buskingId, userId);
    }
}