package kr.texturized.muus.util;

import kr.texturized.muus.common.util.PasswordEncryptor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordEncryptorTest {
    @Test
    void passwordEncode() {
        final String rawPassword = "qwerasdf12342";

        final String encodedPassword = PasswordEncryptor.encrypt(rawPassword);
        System.out.println("encodedPassword = " + encodedPassword);

        Assertions.assertThat(rawPassword).isNotEqualTo(encodedPassword);
        Assertions.assertThat(PasswordEncryptor.matches(rawPassword, encodedPassword)).isTrue();
    }
}
