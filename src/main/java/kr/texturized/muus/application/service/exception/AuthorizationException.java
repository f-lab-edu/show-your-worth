package kr.texturized.muus.application.service.exception;

import kr.texturized.muus.common.error.exception.BusinessException;
import kr.texturized.muus.common.error.exception.ErrorCode;

/**
 * Exception for authorization.
 */
public class AuthorizationException extends BusinessException {

    public AuthorizationException() {
        super(ErrorCode.UNATHORIZED);
    }
}
