package com.jeremw.example.emailsender.email;

import jakarta.mail.MessagingException;
import org.thymeleaf.context.Context;

import org.springframework.stereotype.Service;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@Service
public interface EmailService {

	void sendRowText(String to, String subject,String content) throws MessagingException;

	void sentHtmlTemplate(String to, String subject, String templateName, Context context) throws MessagingException;
}
