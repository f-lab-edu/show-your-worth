package kr.texturized.muus.domain.entity;

import java.util.Arrays;
import kr.texturized.muus.domain.exception.PostCategoryNotFoundException;
import lombok.Getter;

/**
 * PostCategory.
 */
@Getter
public enum PostCategoryEnum {

    BUSKING(1),
    FEED(2),
    NOTICE(3)

    ;

    private final int value;

    PostCategoryEnum(final int value) {
        this.value = value;
    }

    public static PostCategoryEnum fromKey(String key) {
        int keyValue = Integer.parseInt(key);
        return fromKey(keyValue);
    }

    /**
     * Integer value to enum instance.
     *
     * @param key index in database
     * @return enum class mapping with key
     */
    public static PostCategoryEnum fromKey(int key) {
        return Arrays.stream(values())
            .filter(type -> type.value == key)
            .findAny()
            .orElseThrow(() -> new PostCategoryNotFoundException(key));
    }

}
