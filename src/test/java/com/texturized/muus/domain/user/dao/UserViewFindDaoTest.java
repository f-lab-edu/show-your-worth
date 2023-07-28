package com.texturized.muus.domain.user.dao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.texturized.muus.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class UserViewFindDaoTest extends IntegrationTest {

    @Test
    void MyBatis_기반_유저_정보_일괄_조회() throws Exception {
        mvc.perform(get("/users/"));
    }
}
