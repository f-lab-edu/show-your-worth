package kr.texturized.muus.domain.exception;

import kr.texturized.muus.common.error.exception.BusinessException;
import kr.texturized.muus.common.error.exception.ErrorCode;

/**
 * Exception for failure to PostCategory mapping in ORM Framework.
 */
public class PostCategoryNotFoundException extends BusinessException {

    public PostCategoryNotFoundException(int value) {
        super("No matched value: " + value + " in PostCategory", ErrorCode.INVALID_TYPE_VALUE);
    }
}
