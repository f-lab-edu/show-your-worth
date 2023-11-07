package kr.texturized.muus.common.util;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.texturized.muus.application.service.SignInOutService;
import kr.texturized.muus.application.service.UserService;
import kr.texturized.muus.domain.entity.UserTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class SignInInterceptor implements HandlerInterceptor {

    private final SignInOutService signInOutService;
    private final UserService userService;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler) throws Exception {

        final SignInCheck signInCheck = checkAnnotation(handler);
        if (null != signInCheck) {
            final String currentAccountId = signInOutService.getCurrentAccountId();
            UserTypeEnum userType = userService.getAccountIdUserType(currentAccountId);
            if (!Arrays.stream(signInCheck.userType()).anyMatch(type -> type == userType)) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
            }
        }

        return true;
    }

    private SignInCheck checkAnnotation(Object handler) {
        SignInCheck annotation = null;
        if (handler instanceof HandlerMethod handlerMethod) {
            annotation = handlerMethod.getMethodAnnotation(SignInCheck.class);
        }
        return annotation;
    }
}
