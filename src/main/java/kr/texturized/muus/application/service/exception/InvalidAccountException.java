package kr.texturized.muus.application.service.exception;

import kr.texturized.muus.common.error.exception.BusinessException;
import kr.texturized.muus.common.error.exception.ErrorCode;

public class InvalidAccountException extends BusinessException {

    public InvalidAccountException() {
        super(ErrorCode.INVALID_ACCOUNT);
    }
}
