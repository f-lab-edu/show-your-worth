package kr.texturized.muus.common.signin;

/**
 * Interface for sign-in management.
 */
public interface SignInManager {

    /**
     * Sign-in.
     */
    void create();

    /**
     * Check whether this account is sign-in.
     */
    void read();

    /**
     * Update sign-in session(or refresh).
     */
    void update();

    /**
     * Sign-up.
     */
    void delete();
}
