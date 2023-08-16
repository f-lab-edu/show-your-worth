package kr.texturized.muus.application.service.exception;

import kr.texturized.muus.common.error.exception.BusinessException;
import kr.texturized.muus.common.error.exception.ErrorCode;

public class AlreadySignedUpEmailException extends BusinessException {

    public AlreadySignedUpEmailException() {
        super(ErrorCode.ALREADY_SIGNED_UP_EMAIL);
    }
}
