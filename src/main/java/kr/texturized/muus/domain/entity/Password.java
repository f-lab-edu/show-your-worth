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
public class Password {

    @Column(name = "password", length = 20)
    private String value;

    public Password(String value) {
        this.value = value;
    }
}
