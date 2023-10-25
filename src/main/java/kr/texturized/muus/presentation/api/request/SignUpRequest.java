package kr.texturized.muus.presentation.api.request;

import static kr.texturized.muus.common.util.ValidationConstants.*;

import javax.validation.constraints.Pattern;
import kr.texturized.muus.domain.vo.SignUpVo;

/**
 * Request for sign-up.
 */
public record SignUpRequest(
    @Pattern(regexp = ACCOUNT_PATTERN, message = ACCOUNT_PATTERN_INVALID_MESSAGE)
    String accountId,
    @Pattern(regexp = NICKNAME_PATTERN, message = NICKNAME_PATTERN_INVALID_MESSAGE)
    String nickname,
    @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_PATTERN_INVALID_MESSAGE)
    String password
) {

    public SignUpVo toDto() {
        return new SignUpVo(accountId, password, nickname);
    }
}
