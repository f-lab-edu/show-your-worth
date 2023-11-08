package kr.texturized.muus.application.service.exception;

import kr.texturized.muus.common.error.exception.BusinessException;
import kr.texturized.muus.common.error.exception.ErrorCode;

public class AlreadySignoutException extends BusinessException {

    public AlreadySignoutException() {
        super(ErrorCode.FAILED_TO_SIGNOUT);
    }
}
