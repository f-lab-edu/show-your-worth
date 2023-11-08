package kr.texturized.muus.common.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kr.texturized.muus.domain.entity.UserTypeEnum;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SignInCheck {
    UserTypeEnum[] userType();
}
