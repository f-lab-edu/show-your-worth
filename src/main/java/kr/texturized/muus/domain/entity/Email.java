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
public class Email {

    @Column(name = "email_account", length = 45)
    private String value;

    public Email(String value) {
        this.value = value;
    }
}
