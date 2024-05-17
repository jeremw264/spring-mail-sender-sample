package com.jeremw.example.emailsender.email;

import com.jeremw.example.emailsender.email.dto.RawEmailForm;
import com.jeremw.example.emailsender.email.dto.TemplateEmailForm;
import com.jeremw.example.emailsender.exception.ResourceExceptionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling email sending operations.
 * <p>
 * This controller provides endpoints for sending emails either with raw data or using templates.
 * </p>
 *
 * <p>
 * Each method processes the incoming request data, sends an email accordingly, and returns a response
 * indicating the result of the operation.
 * </p>
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@Tag(name = "Email Endpoints")
@RestController
@RequestMapping("/email")
public interface EmailController {

	/**
	 * Handles HTTP POST requests to send raw email data.
	 *
	 * <p>This method receives a JSON object representing raw email data in the request body,
	 * processes it, and sends an email accordingly. It returns a {@link ResponseEntity} with a
	 * status message indicating the result of the email sending operation.</p>
	 *
	 * @param rawEmailForm an instance of {@link RawEmailForm} containing the raw email data to be sent
	 * @return a {@link ResponseEntity} containing a status message as a {@link String}
	 * @throws MessagingException if there is an error while attempting to send the email
	 */
	@Operation(summary  = "Send Raw Email Data", description = "Processes and sends raw email data provided in the request body.")
	@ApiResponse(responseCode = "200", description = "Email sent successfully.")
	@ApiResponse(responseCode = "400", description = "Invalid input data.",
			content = @Content(schema = @Schema(implementation = ResourceExceptionDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal server error.",
			content = @Content(schema = @Schema(implementation = ResourceExceptionDTO.class)))
	@PostMapping("/raw")
	ResponseEntity<String> sendRowData(@RequestBody RawEmailForm rawEmailForm) throws MessagingException;

	/**
	 * Handles HTTP POST requests to send email data using a template.
	 *
	 * <p>This method receives a JSON object representing email data with a template in the request body,
	 * processes it, and sends an email accordingly. It returns a {@link ResponseEntity} with a
	 * status message indicating the result of the email sending operation.</p>
	 *
	 * @param templateEmailForm an instance of {@link TemplateEmailForm} containing the template email data to be sent
	 * @return a {@link ResponseEntity} containing a status message as a {@link String}
	 * @throws MessagingException if there is an error while attempting to send the email
	 */
	@Operation(summary = "Send email data using a template")
	@ApiResponse(responseCode = "200", description = "Email sent successfully.")
	@ApiResponse(responseCode = "400", description = "Invalid input data.",
			content = @Content(schema = @Schema(implementation = ResourceExceptionDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal server error.",
			content = @Content(schema = @Schema(implementation = ResourceExceptionDTO.class)))
	@PostMapping("/template")
	ResponseEntity<String> sendTemplateData(@RequestBody TemplateEmailForm templateEmailForm) throws MessagingException;
}
