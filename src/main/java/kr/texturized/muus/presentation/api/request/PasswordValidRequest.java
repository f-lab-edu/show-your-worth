package kr.texturized.muus.presentation.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record PasswordValidRequest (
    @NotBlank(message = "비밀번호 입력은 필수에요.")
    @Pattern(
        regexp = "[A-Za-z\\d~!@#$%^&*]{6,20}",
        message = "비밀번호는 6~20자로 입력해주세요(특수 문자는 ~,!,@,#,$,%,^,&,*만 허용해요)."
    )
    String password
){

}
