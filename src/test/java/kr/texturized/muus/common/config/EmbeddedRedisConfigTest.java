package kr.texturized.muus.common.config;

import static org.assertj.core.api.Assertions.*;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import kr.texturized.muus.test.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

class EmbeddedRedisConfigTest extends IntegrationTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
    static record TestVo (
        Long id,
        String name
    ){

    }

    private void addItem(TestVo vo) {
        redisTemplate.opsForValue().set(vo.id().toString(), vo);
        redisTemplate.expire(vo.id().toString(), 1, TimeUnit.SECONDS);
    }
    
    private TestVo findById(Long id) {
        return (TestVo)redisTemplate.opsForValue().get(id.toString());
    }

    private void deleteItem(Long id) {
        redisTemplate.delete(id.toString());
    }

    @Test
    @DisplayName("Redis에 데이터 저장 시 정상적으로 조회되어야 함")
    void saveTest() {
        addItem(new TestVo(1L, "user"));
        TestVo vo = findById(1L);
        System.out.println("vo.id = " + vo.id);
        System.out.println("vo.name = " + vo.name);
    }

    @Test
    @DisplayName("Redis 에 데이터를 삭제하면 정상적으로 삭제되어야 함")
    void deleteTest() {
        addItem(new TestVo(1L, "user"));
        TestVo vo = findById(1L);
        deleteItem(vo.id);
        vo = findById(1L);
        assertThat(vo).isNull();
    }
}