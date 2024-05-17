package com.jeremw.example.emailsender.email;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.jeremw.example.emailsender.email.dto.RawEmailForm;
import com.jeremw.example.emailsender.email.dto.TemplateEmailForm;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.context.Context;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link EmailController}.
 *
 * <p>
 * This class provides the implementation for handling email sending operations specified in the {@link EmailController} interface.
 * </p>
 *
 * <p>
 * It contains methods to send emails either with raw data or using templates. Each method delegates the email sending process to the {@link EmailService}.
 * </p>
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmailControllerImpl implements EmailController {

	private final static String TEMPLATE_EMAIL_SUBJECT = "Bienvenue chez Votre Société";

	private final EmailService emailService;

	/**
	 * Handles HTTP POST requests to send raw email data.
	 *
	 * <p>This method receives a {@link RawEmailForm} object representing raw email data,
	 * extracts necessary information (recipient, subject, content), and delegates the email sending process to the {@link EmailService}.
	 * It then returns a {@link ResponseEntity} indicating the success of the operation.</p>
	 *
	 * @param rawEmailForm an instance of {@link RawEmailForm} containing the raw email data to be sent
	 * @return a {@link ResponseEntity} indicating the success of the email sending operation
	 * @throws MessagingException if there is an error while attempting to send the email
	 */
	@Override
	public ResponseEntity<String> sendRowData(final RawEmailForm rawEmailForm) throws MessagingException {
		emailService.sendRowText(rawEmailForm.getTo(), rawEmailForm.getSubject(), rawEmailForm.getContent());
		log.info("Raw email successfully sent to {}", rawEmailForm.getTo());
		return ResponseEntity.ok("Raw email sent successfully.");
	}

	/**
	 * Handles HTTP POST requests to send email data using a template.
	 *
	 * <p>This method receives a {@link TemplateEmailForm} object representing email data with a template,
	 * prepares necessary variables (such as name, email, signup date) and delegates the email sending process to the {@link EmailService}.
	 * It then returns a {@link ResponseEntity} indicating the success of the operation.</p>
	 *
	 * @param templateEmailForm an instance of {@link TemplateEmailForm} containing the template email data to be sent
	 * @return a {@link ResponseEntity} indicating the success of the email sending operation
	 * @throws MessagingException if there is an error while attempting to send the email
	 */
	@Override
	public ResponseEntity<String> sendTemplateData(final TemplateEmailForm templateEmailForm) throws MessagingException {
		Map<String, Object> variables = prepareTemplateVariables(templateEmailForm);
		Context context = new Context();
		context.setVariables(variables);

		emailService.sentHtmlTemplate(templateEmailForm.getTo(), TEMPLATE_EMAIL_SUBJECT, "example-template", context);
		log.info("Template email successfully sent to {}", templateEmailForm.getTo());
		return ResponseEntity.ok("Template email sent successfully.");
	}

	private Map<String, Object> prepareTemplateVariables(TemplateEmailForm templateEmailForm) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("subject", TEMPLATE_EMAIL_SUBJECT);
		variables.put("name", templateEmailForm.getName());
		variables.put("email", templateEmailForm.getTo());
		variables.put("signupDate", LocalDate.now().toString());
		variables.put("companyName", templateEmailForm.getCompanyName());
		variables.put("currentYear", LocalDate.now().getYear());
		return variables;
	}
}
