package kr.texturized.muus.application.service.exception;

import kr.texturized.muus.common.error.exception.BusinessException;
import kr.texturized.muus.common.error.exception.ErrorCode;

public class OutOfCoordinateRangeException extends BusinessException {

    public OutOfCoordinateRangeException() {
        super(ErrorCode.COORDINATE_IS_OUT_OF_RANGE);
    }
}
