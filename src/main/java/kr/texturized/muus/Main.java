package kr.texturized.muus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * @EnableRedisHttpSession: Use Redis as a session storage rather than default server session storage.
 * It creates bean for filter named 'springSesionRepositoryFilter'.
 * springSesionRepositoryFilter changes HttpSession to customized concretion supported by spring session.
 * Redis suppports spring session where its annotation declared.
 *
 * Since its annotation activates not only test profile, test case doesn't work well.
 * Set this property in application.yml(spring.session.store-type)(It works same as annotation).
 */
@SpringBootApplication
//@EnableRedisHttpSession
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
