package kr.texturized.muus.application.service.exception;

import kr.texturized.muus.common.error.exception.BusinessException;
import kr.texturized.muus.common.error.exception.ErrorCode;

public class DuplicatedAccountIdException extends BusinessException {

    public DuplicatedAccountIdException() {
        super(ErrorCode.DUPLICATED_ACCOUNT_ID);
    }
}
