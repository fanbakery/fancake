package com.fanbakery.fancake.module.join.model;

import com.fanbakery.fancake.code.service.member.MbRouteCd;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;


@Data
public class JoinInfoReq {
    private MbRouteCd mbRoute;      //email,sns

    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String userMail;

    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message ="비밀번호는 영문, 숫자를 포함하여 8글자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", message ="비밀번호는 대소문, 숫자, 특수문자를 포함하여 8글자 이상이어야 합니다.")
    private String userPw;

    //@Size(min=2, message = "2글자 이상 이름을 입력해주세요")
    private String userName;

    //@NotBlank(message = "닉네임은 2~10 글사이로 입력하셔야 합니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|_|]{2,10}$", message = "닉네임은 2~10 글사이로 입력하셔야 합니다.")
    private String userNick;

    @Pattern(regexp = "^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$", message = "핸드폰 번호를 입력해야 합니다.(숫자만입력)")
    private String userPhone;

    @NotBlank(message = "핸드폰 인증을 해야 합니다.")
    private String userCert;

    @NotNull(message = "이용양관 및 개인정보 취급방침 동의여부를 확인해야 합니다.")
    private boolean agree;


    private MultipartFile userProf;

    //-- 임시 파일명 경로
    private String userProfTempUrlPath;
    private String userProfTempFileName;
    // 실제 프로파일 URL 경로
    private String userProfRealFileUrl;

    public boolean checkUserNick() {
        /*if( this.userNick.isEmpty() ) {
            this.userNick = this.userName;
        }*/
        return java.util.regex.Pattern.matches("^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|_|]{2,10}$", this.userNick);
    }
}
