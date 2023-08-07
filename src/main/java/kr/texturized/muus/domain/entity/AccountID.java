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
public class AccountID {

    @Column(name = "account_id", length = 15)
    private String value;

    public AccountID(String value) {
        this.value = value;
    }
}
