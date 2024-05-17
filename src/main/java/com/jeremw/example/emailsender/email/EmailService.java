package com.jeremw.example.emailsender.email;

import jakarta.mail.MessagingException;
import org.thymeleaf.context.Context;

import org.springframework.stereotype.Service;

/**
 * Service interface for sending emails.
 *
 * <p>
 * This interface defines methods for sending plain text emails and emails using HTML templates.
 * Implementations of this interface should provide the actual email sending logic.
 * </p>
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@Service
public interface EmailService {

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
	void sendRowText(String to, String subject, String content) throws MessagingException;

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
	void sentHtmlTemplate(String to, String subject, String templateName, Context context) throws MessagingException;
}
