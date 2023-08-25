package kr.texturized.muus.common.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;

@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Unsupported HTTP Method"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", " Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", " Access is Denied"),

    // User
    DUPLICATED_ACCOUNT_ID(400, "C101", " 이미 사용중인 아이디에요"),
    DUPLICATED_NICKNAME(400, "C102", " 이미 사용중인 닉네임이에요"),
    ALREADY_SIGNED_UP_EMAIL(400, "C103", " 이미 회원가입한 주소에요"),
    INVALID_ACCOUNT(400, "C104", " 잘못된 회원정보에요"),

    // Busking
    COORDINATE_IS_OUT_OF_RANGE(400, "C201", " 범위를 넘어섰어요")

    ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
