package kr.texturized.muus.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@Profile("local")
@Configuration
public class LocalRedisConfig {

    @Value("${spring.redis.session.host}")
    private String redisSessionHost;

    @Value("${spring.redis.session.port}")
    private int redisSessionPort;

    private static final String REDIS_IMAGE = "redis:7.2.2-alpine3.18";

    private static final GenericContainer<?> REDIS_CONTAINER;

    static {
        REDIS_CONTAINER = new GenericContainer<>(DockerImageName.parse(REDIS_IMAGE))
            .withExposedPorts(6379)
            .withReuse(true);

        REDIS_CONTAINER.start();

        System.setProperty("spring.redis.session.host", REDIS_CONTAINER.getHost());
        System.setProperty("spring.redis.session.port", REDIS_CONTAINER.getMappedPort(6379).toString());
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisSessionHost, redisSessionPort);
    }
}
