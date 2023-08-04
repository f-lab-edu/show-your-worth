package kr.texturized.muus.application.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import kr.texturized.muus.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class UserViewServiceTest extends IntegrationTest {

    @Test
    void MyBatis_기반_유저_정보_일괄_조회() throws Exception {
        mvc.perform(get("/users"));
    }
}
