package com.jeremw.example.emailsender.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;

/**
 * Data Transfer Object (DTO) class representing information about a resource-related
 * exception.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object (DTO) representing information about a resource-related exception.")
public class ResourceExceptionDTO {

	/**
	 * The unique identifier for the type of error.
	 */
	@Schema(description = "The unique identifier for the type of error.", example = "RESOURCE_NOT_FOUND")
	private String errorCode;

	/**
	 * The human-readable error message.
	 */
	@Schema(description = "The human-readable error message.", example = "Resource not found.")
	private String errorMessage;

	/**
	 * The URL of the HTTP request that triggered the exception.
	 */
	@Schema(description = "The URL of the HTTP request that triggered the exception.", example = "http://example.com/resource")
	private String requestURL;

	/**
	 * The HTTP status associated with the exception.
	 */
	@Schema(description = "The HTTP status associated with the exception.", example = "NOT_FOUND")
	private HttpStatus status;

}
