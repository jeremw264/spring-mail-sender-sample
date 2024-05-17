package com.jeremw.example.emailsender.email.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


/**
 * Data transfer object for raw email form.
 *
 * <p>
 * This class represents the data required to send a raw email, including the recipient's email address,
 * the subject of the email, and the content of the email.
 * </p>
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@Data
@Schema(description = "Data transfer object for raw email form")
public class RawEmailForm {

	/**
	 * The recipient's email address.
	 */
	@Schema(description = "The recipient's email address", example = "recipient@example.com")
	@Email(message = "The email address must be valid.")
	@NotBlank(message = "The recipient's email address is required.")
	private String to;

	/**
	 * The subject of the email.
	 */
	@Schema(description = "The subject of the email", example = "Welcome!", maxLength = 100)
	@NotBlank(message = "The subject is required.")
	@Size(max = 100, message = "The subject must not exceed 100 characters.")
	private String subject;

	/**
	 * The content of the email.
	 */
	@Schema(description = "The content of the email", example = "Hello, welcome to our service.")
	@NotBlank(message = "The content is required.")
	private String content;
}