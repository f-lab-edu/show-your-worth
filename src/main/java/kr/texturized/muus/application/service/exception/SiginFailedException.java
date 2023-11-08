package kr.texturized.muus.application.service.exception;

import kr.texturized.muus.common.error.exception.BusinessException;
import kr.texturized.muus.common.error.exception.ErrorCode;

public class SiginFailedException extends BusinessException {

    public SiginFailedException() {
        super(ErrorCode.FAILED_TO_SIGNIN);
    }
}
