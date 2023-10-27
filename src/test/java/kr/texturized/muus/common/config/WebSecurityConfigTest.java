package kr.texturized.muus.common.config;

import kr.texturized.muus.test.IntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

class WebSecurityConfigTest extends IntegrationTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void passwordEncode() {
        final String rawPassword = "qwerasdf12342";

        final String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("encodedPassword = " + encodedPassword);

        Assertions.assertThat(rawPassword).isNotEqualTo(encodedPassword);
        Assertions.assertThat(passwordEncoder.matches(rawPassword, encodedPassword)).isTrue();
    }
}