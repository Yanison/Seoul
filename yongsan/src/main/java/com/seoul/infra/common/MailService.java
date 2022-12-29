package com.seoul.infra.common;

import java.util.Properties;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	private int authCode = 0;
	
	private Properties getMailsenderProperties() {
		/*
		 * 구글 smtp 프로토콜을 사용하기 위해 설정해야할 프로퍼티들 입니다. 
		 */
        Properties properties = new Properties();
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.ssl.trust","smtp.gmail.com");
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        return properties;
    }
	
	private void creatAuthCode() {
		/*
		 * 인증번호를 생성하기위한 
		 * 111111~ 999999 사이의 무작위의 6자리 숫자를 생성하는 메소드입니다. 
		 */
		Random rand = new Random();
    	int randNumSix = rand.nextInt(888888) + 111111;
    	authCode = randNumSix;
    	System.out.println("생성된 인증번호 randNumSix :: " + authCode);
	}
	
	private MimeMessagePreparator setMailForm(String email){
		/*
		 * 발신할 메일 내용을 구성하기 위한 메소드입니다.
		 * MimeMessagePreparator 객체를 구현하여 구성한 메일 정보를 설정하고 반환합니다.
		 * MimeMessageHelper객체에서 제공해주는 setter로 담습니다.
		 */
		creatAuthCode();
		String content = 
			"황비트 인증번호 입니다." +  
		    "<br><br>" + 
		    "인증 번호는 " + authCode + "입니다." + 
		    "<br>" + 
		    "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
			
		 final MimeMessagePreparator preparator = new MimeMessagePreparator() {
		        
		        public void prepare(MimeMessage mimeMessage) throws Exception {
		        	
		        final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		            helper.setFrom("ghkddi878@gmail.com");
		            helper.setTo(email);
		            helper.setSubject("황비트 인증메일 입니다.");
		            helper.setText(content, true); 
		        }
		 };
		 return preparator;
	}
	
	public String sendMailViaSmtpGmail(String email) {
		
		/*
		 * stmp 프로토콜에 접속하기 위한 인증 정보를 설정합니다.
		 * 구글의 경우 
		 * 어플리케이션에서 요청할 때 접속 요청할 아이디를(Username) 구글 계정 이메일을 사용하고 
		 * 구글 stmp 설정하면서 발급받은 앱 비밀번호를 Password로 사용합니다.
		 * 인증번호를 생성하여 클라이언트쪽으로 반환합니다. 
		 */
		
		mailSender.setHost("smtp.gmail.com");
	    mailSender.setUsername("ghkddi878@gmail.com");
	    mailSender.setPassword("dtsjzmcdbqefpkgq");
	    mailSender.setPort(587);
	    mailSender.setJavaMailProperties(getMailsenderProperties());
	    mailSender.send(setMailForm(email));
	    
	    int createdAuthCode = authCode;
		System.out.println("createdAuthCode :: " + createdAuthCode);
	    
	    return Integer.toString(createdAuthCode);
	}
	
}
