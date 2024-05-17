package com.jeremw.example.emailsender.exception;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 18/05/2024
 */
@ExtendWith(MockitoExtension.class)
class GlobalHandlerTests {

	@Mock
	private HttpServletRequest request;

	@InjectMocks
	private GlobalHandler globalExceptionHandler;

	@Test
	void unknownError_ShouldReturnInternalServerError() {
		Exception exception = new Exception("Unknown error");
		when(request.getRequestURL()).thenReturn(new StringBuffer("URL"));

		ResponseEntity<ResourceExceptionDTO> response = globalExceptionHandler.unknownError(request, exception);

		assertNotNull(response);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("INTERNAL_SERVER_ERROR", response.getBody().getErrorCode());
		assertEquals("Unknown error", response.getBody().getErrorMessage());
		assertEquals(request.getRequestURL().toString(), response.getBody().getRequestURL());
	}

	@Test
	void resourceError_ShouldReturnCustomErrorResponse() {
		ResourceException exception = new ResourceException("CustomError", "Custom error message",
				HttpStatus.NOT_FOUND);
		when(request.getRequestURL()).thenReturn(new StringBuffer("URL"));

		ResponseEntity<ResourceExceptionDTO> response = globalExceptionHandler.resourceError(request, exception);

		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("CustomError", response.getBody().getErrorCode());
		assertEquals("Custom error message", response.getBody().getErrorMessage());
		assertEquals(request.getRequestURL().toString(), response.getBody().getRequestURL());
	}

	@Test
	void handleMethodArgumentNotValid_ShouldReturnBadRequest() {
		MethodArgumentNotValidException exception = Mockito.mock(MethodArgumentNotValidException.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		List<ObjectError> errors = List.of(new ObjectError("field", "message"));

		when(bindingResult.getAllErrors()).thenReturn(errors);
		when(exception.getBindingResult()).thenReturn(bindingResult);
		when(request.getRequestURL()).thenReturn(new StringBuffer("URL"));

		ServletWebRequest servletWebRequest = Mockito.mock(ServletWebRequest.class);
		when(servletWebRequest.getRequest()).thenReturn(request);

		ResponseEntity<Object> response = globalExceptionHandler.handleMethodArgumentNotValid(exception, null,
				HttpStatus.BAD_REQUEST, servletWebRequest);

		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("FormValidationError", ((ResourceExceptionDTO) response.getBody()).getErrorCode());
		assertEquals("message, ", ((ResourceExceptionDTO) response.getBody()).getErrorMessage());
		assertEquals(request.getRequestURL().toString(), ((ResourceExceptionDTO) response.getBody()).getRequestURL());
	}

}