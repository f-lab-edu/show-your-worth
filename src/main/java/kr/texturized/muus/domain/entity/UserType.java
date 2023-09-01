package kr.texturized.muus.domain.entity;

import java.util.Arrays;
import kr.texturized.muus.domain.exception.UserTypeNotFoundException;
import lombok.Getter;

/**
 * UserType.
 */
@Getter
public enum UserType {
    USER(1),
    ADMIN(100)

    ;

    private final int value;

    UserType(final int value) {
        this.value = value;
    }

    public static UserType fromKey(String key) {
        int keyValue = Integer.parseInt(key);
        return fromKey(keyValue);
    }

    public static UserType fromKey(int key) {
        return Arrays.stream(UserType.values())
            .filter(type -> type.value == key)
            .findAny()
            .orElseThrow(() -> new UserTypeNotFoundException(key));
    }
}
