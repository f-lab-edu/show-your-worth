package kr.texturized.muus.domain.vo;

/**
 * VO for Sign-in Service.
 *
 * @param accountId Account ID
 * @param password Password
 */
public record SignInVo(
    String accountId,
    String password
) {

}
