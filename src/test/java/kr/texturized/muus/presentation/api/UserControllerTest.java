package kr.texturized.muus.presentation.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import kr.texturized.muus.common.error.exception.ErrorCode;
import kr.texturized.muus.test.IntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@Slf4j
public class UserControllerTest extends IntegrationTest {

    @Test
    void whenInvalidAccountIdThenReturnException() throws Exception {
        postValidateJsonAndExpectError("/users/validate/account", "accountId","", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/account", "accountId","asdfe", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/account", "accountId","Asd12", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/account", "accountId","asdfqwer12!", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/account", "accountId","12!DSsaFfqwer", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/account", "accountId","#####", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/account", "accountId","1q2w3e4r1!", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/account", "accountId","asdfqwerzxcvasdfqwer", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/account", "accountId","f-lab.kr", ErrorCode.INVALID_INPUT_VALUE);
    }

    @Test
    void whenValidAccountIdThenReturnAccepted() throws Exception {
        postValidateJsonAndExpectAccepted("/users/validate/account","accountId", "redgem92");
        postValidateJsonAndExpectAccepted("/users/validate/account","accountId", "12qewrsadf");
        postValidateJsonAndExpectAccepted("/users/validate/account","accountId", "123456789");
        postValidateJsonAndExpectAccepted("/users/validate/account","accountId", "zxcvasdfewr");
        postValidateJsonAndExpectAccepted("/users/validate/account","accountId", "REDGEM92");
        postValidateJsonAndExpectAccepted("/users/validate/account","accountId", "Redgem92");
        postValidateJsonAndExpectAccepted("/users/validate/account","accountId", "jisuJiUS12");
    }

    @Test
    void whenInvalidPasswordThenReturnException() throws Exception {
        postValidateJsonAndExpectError("/users/validate/password", "password","", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/password", "password","ASDFQ", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/password", "password","1!AS", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/password", "password","1q2w3e4r1!((", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/password", "password","한글도안됨ㅎ", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/password", "password","ASㅎㅁㄴㅇㄹㄴ", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/password", "password","asdfqwerasdf)-+_", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/password", "password","SDFAERGSGBSRTYRTHfdfweyro3fhefaidaosidf", ErrorCode.INVALID_INPUT_VALUE);
    }

    @Test
    void whenValidPasswordThenReturnAccepted() throws Exception {
        postValidateJsonAndExpectAccepted("/users/validate/password","password", "1q2w3e4r1!");
        postValidateJsonAndExpectAccepted("/users/validate/password","password", "1234567@@#$");
        postValidateJsonAndExpectAccepted("/users/validate/password","password", "123456789");
        postValidateJsonAndExpectAccepted("/users/validate/password","password", "zxcvasdfewr");
        postValidateJsonAndExpectAccepted("/users/validate/password","password", "JIsue%$#@@#%^&");
        postValidateJsonAndExpectAccepted("/users/validate/password","password", "!@#$%^&*");
        postValidateJsonAndExpectAccepted("/users/validate/password","password", "~!@#$%^&DSacvzxc");
    }

    @Test
    void whenInvalidNicknameThenReturnException() throws Exception {
        postValidateJsonAndExpectError("/users/validate/nickname", "nickname","", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/nickname", "nickname","a", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/nickname", "nickname","ㅎㅎㅎㅎㅎ", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/nickname", "nickname","@.@", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/nickname", "nickname","오빠라고불러다오!", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/nickname", "nickname","김미어콜베이베~베이베~", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/nickname", "nickname","kamehameha^^", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/nickname", "nickname","~崔志秀~", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/nickname", "nickname","Songoku's げんきぎょく", ErrorCode.INVALID_INPUT_VALUE);
        postValidateJsonAndExpectError("/users/validate/nickname", "nickname","김미김미나 김미김미나 뚜두두두두", ErrorCode.INVALID_INPUT_VALUE);
    }

    @Test
    void whenValidNicknameThenReturnAccepted() throws Exception {
        postValidateJsonAndExpectAccepted("/users/validate/nickname","nickname", "MX");
        postValidateJsonAndExpectAccepted("/users/validate/nickname","nickname", "MX");
        postValidateJsonAndExpectAccepted("/users/validate/nickname","nickname", "F-lab");
        postValidateJsonAndExpectAccepted("/users/validate/nickname","nickname", "jisus.choi");
        postValidateJsonAndExpectAccepted("/users/validate/nickname","nickname", "崔志秀");
        postValidateJsonAndExpectAccepted("/users/validate/nickname","nickname", "Songoku-げんきぎょく");
        postValidateJsonAndExpectAccepted("/users/validate/nickname","nickname", "_--_");
        postValidateJsonAndExpectAccepted("/users/validate/nickname","nickname", "im-so-sexy");
        postValidateJsonAndExpectAccepted("/users/validate/nickname","nickname", "strong_minsu");
    }

    void postValidateJsonAndExpectError(
        final String uri,
        final String key,
        final String value,
        final ErrorCode errorCode
    )
        throws Exception {
        mvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\""+ key + "\" : \"" + value + "\"}"))
            .andDo(print())
            .andExpect(jsonPath("message").value(errorCode.getMessage()))
            .andExpect(jsonPath("status").value(errorCode.getStatus()))
            .andExpect(jsonPath("code").value(errorCode.getCode()))
            .andExpect(jsonPath("errors").isNotEmpty());
    }

    void postValidateJsonAndExpectAccepted(final String uri, final String key, final String value)
        throws Exception {
        mvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"" + key + "\" : \"" + value + "\"}"))
            .andDo(print())
            .andExpect(status().isAccepted());
    }

}
