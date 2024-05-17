package com.jeremw.example.emailsender.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Implementation of the {@link EmailService} interface.
 *
 * <p>
 * This class provides the implementation for sending plain text emails and emails using HTML templates.
 * It uses {@link JavaMailSender} for sending emails and {@link TemplateEngine} for processing HTML templates.
 * </p>
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	/**
	 * The 'from' email address.
	 */
	@Value("${custom.mail.from}")
	private String from;

	private final JavaMailSender javaMailSender;

	private final TemplateEngine templateEngine;

	/**
	 * Sends a plain text email.
	 *
	 * <p>This method takes the recipient's email address, the subject of the email, and the content of the email
	 * as parameters. It throws a {@link MessagingException} if there is an error while attempting to send the email.</p>
	 *
	 * @param to      the recipient's email address
	 * @param subject the subject of the email
	 * @param content the content of the email
	 * @throws MessagingException if there is an error while attempting to send the email
	 */
	@Override
	public void sendRowText(final String to, final String subject, final String content) throws MessagingException {
		try {
			MimeMessage mail = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, false);

			javaMailSender.send(mail);
			log.info("Sent raw text email to {}", to);
		}
		catch (MessagingException e) {
			log.error("Failed to send raw text email to {}: {}", to, e.getMessage());
			throw e;
		}
	}

	/**
	 * Sends an email using an HTML template.
	 *
	 * <p>This method takes the recipient's email address, the subject of the email, the name of the HTML template,
	 * and a {@link Context} object containing variables to be used in the template. It throws a {@link MessagingException}
	 * if there is an error while attempting to send the email.</p>
	 *
	 * @param to           the recipient's email address
	 * @param subject      the subject of the email
	 * @param templateName the name of the HTML template to be used
	 * @param context      a {@link Context} object containing variables for the template
	 * @throws MessagingException if there is an error while attempting to send the email
	 */
	@Override
	public void sentHtmlTemplate(final String to, final String subject, final String templateName, final Context context) throws MessagingException {
		try {
			MimeMessage mail = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);

			String body = templateEngine.process(templateName, context);

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);

			javaMailSender.send(mail);
			log.info("Sent HTML template email to {}", to);
		}
		catch (MessagingException e) {
			log.error("Failed to send HTML template email to {}: {}", to, e.getMessage());
			throw e;
		}
	}
}
