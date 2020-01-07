package com.commute.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendMail {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail( String toMailAddress, String subject, String body ) {

			SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(toMailAddress);

	        msg.setSubject(subject);
	        msg.setText(body);

	        javaMailSender.send(msg);
		
	}

}
