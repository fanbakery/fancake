package com.fanbakery.fancake.api.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.fanbakery.fancake.config.ApplicationConfig;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailService {

    
    private final ApplicationConfig applicationConfig;
    
    public void sendMail(MailTO mail) {
    	
    	JavaMailSenderImpl impl = new JavaMailSenderImpl();
    	impl.setPort(applicationConfig.getEmailPort());
    	impl.setPassword(applicationConfig.getEmailPassword());
    	impl.setUsername(applicationConfig.getEmailUsername());
    	impl.setHost(applicationConfig.getEmailHost());
    	
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
//        message.setFrom(""); from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());
        System.out.println(applicationConfig.getEmailUsername());
        message.setFrom(applicationConfig.getEmailUsername());
        impl.send(message);
        
    }
    
}
