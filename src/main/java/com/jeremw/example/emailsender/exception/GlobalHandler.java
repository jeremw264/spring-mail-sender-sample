package com.jeremw.example.emailsender.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class handles global exceptions in the application, such as unexpected errors,
 * exceptions related to specific resources, access denied errors, and method argument validation errors.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@Slf4j
@RestControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handles unknown errors that occur within the application.
	 *
	 * @param req       The HttpServletRequest in which the error occurred.
	 * @param exception The Exception that was thrown.
	 * @return A ResponseEntity containing details of the error.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResourceExceptionDTO> unknownError(HttpServletRequest req, Exception exception) {

		log.info("An unknown error occurred. Request URL: {}", req.getRequestURL().toString(), exception);

		ResourceExceptionDTO res = ResourceExceptionDTO.builder()
				.errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(4))
				.errorMessage(exception.getMessage())
				.requestURL(req.getRequestURL().toString())
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	}

	/**
	 * Handles errors related to specific resources within the application.
	 *
	 * @param req       The HttpServletRequest in which the error occurred.
	 * @param exception The ResourceException that was thrown.
	 * @return A ResponseEntity containing details of the error.
	 */
	@ExceptionHandler(ResourceException.class)
	public ResponseEntity<ResourceExceptionDTO> resourceError(HttpServletRequest req, ResourceException exception) {

		log.info("An resource exception error occurred. Request URL: {}", req.getRequestURL().toString(), exception);

		ResourceExceptionDTO res = ResourceExceptionDTO.builder()
				.errorCode(exception.getErrorCode() != null ? exception.getErrorCode() : "Undefined")
				.errorMessage(exception.getMessage())
				.requestURL(req.getRequestURL().toString())
				.status(exception.getStatus())
				.build();

		return ResponseEntity.status(exception.getStatus()).body(res);
	}

	/**
	 * Handles method argument validation errors within the application.
	 *
	 * @param exception The MethodArgumentNotValidException that was thrown.
	 * @param headers   The HttpHeaders associated with the request.
	 * @param status    The HttpStatusCode associated with the error.
	 * @param request   The WebRequest containing information about the request.
	 * @return A ResponseEntity containing details of the validation error.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		log.error("Method argument not valid. Request URL: {}", ((ServletWebRequest) request).getRequest()
				.getRequestURL().toString(), exception);

		StringBuilder eMsg = new StringBuilder();
		exception.getBindingResult().getAllErrors().forEach(e -> eMsg.append(e.getDefaultMessage()).append(", "));

		ResourceExceptionDTO res = ResourceExceptionDTO.builder()
				.errorCode("FormValidationError")
				.errorMessage(eMsg.toString())
				.requestURL(((ServletWebRequest) request).getRequest().getRequestURL().toString())
				.status((HttpStatus) status)
				.build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

	}

}
