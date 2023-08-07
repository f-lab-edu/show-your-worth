package kr.texturized.muus.common.error;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import kr.texturized.muus.common.error.exception.ErrorCode;
import kr.texturized.muus.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class CommonExceptionHandlerTest extends IntegrationTest {


    @Test
    public void 지원하지않은_http_method_호출_했을경우() throws Exception {

        //given

        //when
        final ResultActions resultActions = requestGetUser(0L);

        //then
        resultActions
            .andExpect(status().isMethodNotAllowed())
            .andExpect(jsonPath("message").value(ErrorCode.METHOD_NOT_ALLOWED.getMessage()))
            .andExpect(jsonPath("status").value(ErrorCode.METHOD_NOT_ALLOWED.getStatus()))
            .andExpect(jsonPath("code").value(ErrorCode.METHOD_NOT_ALLOWED.getCode()))
            .andExpect(jsonPath("errors").isEmpty())
        ;
    }

    private ResultActions requestGetUser(long memberId) throws Exception {
        return mvc.perform(post("/users/{id}", memberId)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print());
    }
}
