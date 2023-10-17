package kr.texturized.muus.domain.entity;

import java.util.Arrays;
import kr.texturized.muus.domain.exception.UserTypeNotFoundException;
import lombok.Getter;

/**
 * UserType.
 */
@Getter
public enum UserTypeEnum {
    USER(1),
    ADMIN(100)

    ;

    private final int value;

    UserTypeEnum(final int value) {
        this.value = value;
    }

    public static UserTypeEnum fromKey(String key) {
        int keyValue = Integer.parseInt(key);
        return fromKey(keyValue);
    }

    public static UserTypeEnum fromKey(int key) {
        return Arrays.stream(UserTypeEnum.values())
            .filter(type -> type.value == key)
            .findAny()
            .orElseThrow(() -> new UserTypeNotFoundException(key));
    }
}
