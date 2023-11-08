package kr.texturized.muus.application.service;

import java.util.Optional;
import javax.servlet.http.HttpSession;
import kr.texturized.muus.application.service.exception.AlreadySignoutException;
import kr.texturized.muus.application.service.exception.SiginFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/**
 * It manages session by HttpSession DI.
 * Both SessionSignInService is in 'singleton' scope and HttpSession is in 'session' scope.
 * For this difference, Spring injects HttpSession instance creating by dynamic proxy.
 * This is called 'Scoped Proxy'.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SessionSignInOutService implements SignInOutService {
    private static final String ACCOUNT_ID = "ACCOUNT_ID";

    private final HttpSession httpSession;

    @Override
    public String signIn(final String accountId) {
        final String idValue = Optional.of(accountId)
            .orElseThrow(SiginFailedException::new);
        httpSession.setAttribute(ACCOUNT_ID, idValue);

        log.info("Sign-in: {}", idValue);

        return accountId;
    }

    @Override
    public void signOut() {
        final String currentUser = Optional.of(getCurrentAccountId())
            .orElseThrow(AlreadySignoutException::new);

        log.info("Sign-out: {}", currentUser);

        httpSession.removeAttribute(ACCOUNT_ID);
    }

    @Override
    public String getCurrentAccountId() {
        return Optional.ofNullable(httpSession.getAttribute(ACCOUNT_ID))
            .map(attr -> (String)attr)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
    }
}
