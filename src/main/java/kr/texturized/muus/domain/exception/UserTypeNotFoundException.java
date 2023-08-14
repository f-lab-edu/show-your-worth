package kr.texturized.muus.domain.exception;

import kr.texturized.muus.common.error.exception.BusinessException;
import kr.texturized.muus.common.error.exception.ErrorCode;

/**
 * Exception for Failure to UserType Mapping in ORM framework.
 */
public class UserTypeNotFoundException extends BusinessException {

    public UserTypeNotFoundException(int value) {
        super("No matched value: " + value + " in UserType", ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
