package com.jeremw.example.emailsender.exception;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;

/**
 * This exception class represents resource-related errors that may occur within the application.
 * It extends the standard Java {@code Exception} class.
 *
 * <p>
 * This class includes fields for error code, error message, and HTTP status, providing
 * detailed information about the exception.
 * </p>
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@Getter
@Setter
public class ResourceException extends Exception {

	/**
	 * The unique identifier for the type of error.
	 */
	private final String errorCode;

	/**
	 * The HTTP status associated with the exception.
	 */
	private final HttpStatus status;

	/**
	 * Constructs a new ResourceException with the specified error code, error message,
	 * and HTTP status.
	 *
	 * @param errorCode    The unique identifier for the type of error.
	 * @param errorMessage The human-readable error message.
	 * @param status       The HTTP status associated with the exception.
	 */
	public ResourceException(String errorCode, String errorMessage, HttpStatus status) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.status = status;
	}

}