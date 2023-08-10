package kr.texturized.muus.presentation.api.request;

import javax.validation.constraints.Email;

public record EmailRequest(
    @Email(message = "올바른 이메일 형식이 아니에요.")
    String email
){

}
