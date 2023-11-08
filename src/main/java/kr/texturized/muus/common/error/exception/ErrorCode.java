package kr.texturized.muus.common.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;

/**
 * ErrorCode Enum.
 */
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
    DUPLICATED_ACCOUNT_ID(400, "C101", " Already Used Account ID"),
    DUPLICATED_NICKNAME(400, "C102", " Already Used Nickname"),
    ALREADY_SIGNED_UP_EMAIL(400, "C103", " Already Sign-up E-mail"),
    INVALID_ACCOUNT(401, "C104", " Wrong Account Information"),
    FAILED_TO_SIGNIN(401, "C105", " Failed To Sign-in"),
    FAILED_TO_SIGNOUT(401, "C106", " Failed To Sign-out"),

    // Busking
    COORDINATE_IS_OUT_OF_RANGE(400, "C201", " Out of Range for Searching")

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
