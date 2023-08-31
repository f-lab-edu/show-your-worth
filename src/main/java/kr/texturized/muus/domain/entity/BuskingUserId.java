package kr.texturized.muus.domain.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import lombok.RequiredArgsConstructor;

/**
 * Composite-id class for BuskingUser.
 */
@RequiredArgsConstructor
public class BuskingUserId implements Serializable {

    @Column(name = "busking_id")
    private final Long buskingId;

    @Column(name = "user_id")
    private final Long userId;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof BuskingUserId id) {
            return (this.buskingId == id.buskingId && this.userId == id.userId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buskingId, userId);
    }
}
