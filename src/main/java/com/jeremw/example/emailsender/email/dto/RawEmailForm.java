package com.jeremw.example.emailsender.email.dto;

import lombok.Data;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@Data
public class RawEmailForm {
	private String to;
	private String subject;
	private String content;
}
