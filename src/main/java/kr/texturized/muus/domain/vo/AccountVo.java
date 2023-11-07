package kr.texturized.muus.domain.vo;

import kr.texturized.muus.domain.entity.UserTypeEnum;

/**
 * VO for account.
 *
 * @param accountId Account ID
 */
public record AccountVo(
    String accountId,
    UserTypeEnum userType
) {

}
