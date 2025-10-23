package br.com.serratec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {
	
	@Autowired
	private JavaMailSender javaMailSender;

	
	public void enviarEmail(String para, String assunto, String texto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("watkins.victor@gmail.com");
		message.setTo(para);
		message.setSubject(assunto);
		message.setText("Dados do usuario:\n" + texto + "\n\n\n Residência de Software");
		javaMailSender.send(message);
	}
}
