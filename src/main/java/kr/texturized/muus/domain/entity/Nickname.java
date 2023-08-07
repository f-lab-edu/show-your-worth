package kr.texturized.muus.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"value"})
public class Nickname {

    @Column(name = "nickname", length = 15)
    private String value;

    public Nickname(String value) {
        this.value = value;
    }
}
