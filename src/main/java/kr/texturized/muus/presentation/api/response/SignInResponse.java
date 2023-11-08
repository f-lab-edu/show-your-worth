package kr.texturized.muus.presentation.api.response;

import kr.texturized.muus.domain.entity.UserTypeEnum;

public record SignInResponse (
    String result,                  // It could be account id, value or token
                                    // depending on login management system.
    UserTypeEnum userType
){

}
