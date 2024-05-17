package com.jeremw.example.emailsender.email.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Data transfer object for template email form.
 *
 * <p>
 * This class represents the data required to send an email using a template, including the recipient's email address,
 * the recipient's name, and the company name.
 * </p>
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@Data
@Schema(description = "Data transfer object for template email form")
public class TemplateEmailForm {

	/**
	 * The recipient's email address.
	 */
	@Schema(description = "The recipient's email address", example = "recipient@example.com")
	@Email(message = "The email address must be valid.")
	@NotBlank(message = "The recipient's email address is required.")
	private String to;

	/**
	 * The recipient's name.
	 */
	@Schema(description = "The recipient's name", example = "John Doe")
	@NotBlank(message = "The recipient's name is required.")
	private String name;

	/**
	 * The company name.
	 */
	@Schema(description = "The company name", example = "Example Inc.")
	@NotBlank(message = "The company name is required.")
	private String companyName;
}
