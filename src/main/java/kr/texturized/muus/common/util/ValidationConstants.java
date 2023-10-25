package kr.texturized.muus.common.util;

/**
 * Constants for validation.
 */
public interface ValidationConstants {

    String ACCOUNT_PATTERN = "[A-Za-z\\d]{6,15}";
    String ACCOUNT_PATTERN_INVALID_MESSAGE = "아이디는 영문과 숫자 조합 6~15자로 입력해주세요.";

    String PASSWORD_PATTERN = "[A-Za-z\\d~!@#$%^&*]{6,20}";
    String PASSWORD_PATTERN_INVALID_MESSAGE = "비밀번호는 6~20자로 입력해주세요"
                                                                + "(특수 문자는 ~,!,@,#,$,%,^,&,*만 허용해요).";

    String NICKNAME_PATTERN = "[\\x{3041}-\\x{3096}"
                            + "\\x{30A0}-\\x{30FF}"
                            + "\\x{3400}-\\x{4DB5}"
                            + "\\x{4E00}-\\x{9FCB}"
                            + "\\x{F900}-\\x{FA6A}"
                            + "\\x{2E80}-\\x{2FD5}"
                            + "\\x{FF5F}-\\x{FF9F}"
                            + "\\x{3000}-\\x{303F}"
                            + "\\x{31F0}-\\x{31FF}"
                            + "\\x{3220}-\\x{3243}"
                            + "\\x{3280}-\\x{337F}"
                            + "a-zA-Z"
                            + "\\d"
                            + "가-힣"
                            + "\\-"
                            + "_"
                            + ".]"
                            + "{2,15}";
    String NICKNAME_PATTERN_INVALID_MESSAGE = "닉네임은 2~15자로 입력해주세요(특수 문자는 -, _, .만 허용해요).";
}
