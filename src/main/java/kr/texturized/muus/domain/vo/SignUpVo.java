package kr.texturized.muus.domain.vo;

/**
 * VO for Sign-up Service.
 *
 * @param accountId Account ID
 * @param password Password
 * @param nickname Nickname
 */
public record SignUpVo(
    String accountId,
    String password,
    String nickname
) {

}
