package kr.texturized.muus.application.service;

/**
 * Since login logic may change depending on situation, it's implemented by interface.
 * By this architecture for loose coupling called 'Strategy Pattern',
 * Controller(or components using this service) does not need to know specific logic for it.
 */
public interface SignInOutService {

    String signIn(final String accountId);

    void signOut();

    String getCurrentAccountId();
}
