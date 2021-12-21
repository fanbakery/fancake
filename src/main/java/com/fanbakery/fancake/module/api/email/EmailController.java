package com.fanbakery.fancake.module.api.email;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fanbakery.fancake.api.email.MailService;
import com.fanbakery.fancake.api.email.MailTO;
import com.fanbakery.fancake.config.ApplicationConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/test")
public class EmailController {

	@Autowired
	ApplicationConfig _config;
	
	
	@Autowired
    private MailService mailService;
	

	@RequestMapping(value = "/order")
	public ModelAndView order(Model model) {
		model.addAttribute("payer_no", "1234"); 				// 파트너 회원 고유번호
		model.addAttribute("payer_name", "홍길동"); 				// 결제자 이름
		model.addAttribute("payer_hp", "01012345678"); 			// 결제자 휴대전화번호
		model.addAttribute("payer_email", "test@payple.kr"); 	// 결제자 이메일
		model.addAttribute("pay_goods", "휴대폰"); 				// 상품명
		model.addAttribute("pay_total", "1000"); 				// 결제요청금액
		
		ModelAndView andView = new ModelAndView();
		andView.setViewName("order");
		
		return andView;
	}
	
	
    @GetMapping("/email")
    public String sendMail(Model model) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException{
    	
    	 
    	 
		return null;
      }
    
    @PostMapping("/emailSend")
    @ResponseBody
    public String sendMail(MailTO mailTO){

    	
    	mailService.sendMail(mailTO);	
		
        
		return "success";
      }

}
