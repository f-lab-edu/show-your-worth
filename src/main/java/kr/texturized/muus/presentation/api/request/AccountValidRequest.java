package kr.texturized.muus.presentation.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record AccountValidRequest (
    @NotBlank(message = "아이디 입력은 필수에요.")
    @Pattern(
        regexp = "[A-Za-z\\d]{6,15}",
        message = "아이디는 영문과 숫자 조합 6~15자로 입력해주세요."
    )
    String accountId
){
}
