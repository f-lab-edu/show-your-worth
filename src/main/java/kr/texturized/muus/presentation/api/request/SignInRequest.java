package kr.texturized.muus.presentation.api.request;

import kr.texturized.muus.domain.vo.SignInVo;

/**
 * Request for sign-in.
 */
public record SignInRequest(
    String accountId,
    String password
) {

    public SignInVo toDto() {
        return new SignInVo(accountId, password);
    }
}
