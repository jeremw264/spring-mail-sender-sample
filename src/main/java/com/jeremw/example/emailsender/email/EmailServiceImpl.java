package com.jeremw.example.emailsender.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

	/**
	 * The 'from' email address.
	 */
	@Value("${spring.mail.from}")
	private String from;

	private final JavaMailSender javaMailSender;
	private final TemplateEngine templateEngine;

	@Override
	public void sendRowText(String to, String subject,String content) throws MessagingException {
		MimeMessage mail = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);

	}


	@Override
	public void sentHtmlTemplate(String to, String subject, String templateName, Context context) throws MessagingException {
		MimeMessage mail = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);

		String body = templateEngine.process(templateName, context);

		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);


		javaMailSender.send(mail);
	}
}
