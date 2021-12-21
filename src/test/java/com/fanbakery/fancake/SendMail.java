package com.fanbakery.fancake;

import com.fanbakery.fancake.api.email.MailService;
import com.fanbakery.fancake.api.email.MailTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class SendMail {

	
	@Autowired
    private MailService mailService;
	
    @Test
    void contextLoads() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    	MailTO mailTO = MailTO.builder()
    			
    			.address("rhengh01@gmail.com") // 보낼주소
    			.title("밤둘레 님이 발송한 이메일입니다.") //제목
    			.message("message content").build(); //내용
        mailService.sendMail(mailTO);
    }
}
