package com.jeremw.example.emailsender.email.dto;

import lombok.Data;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@Data
public class TemplateEmailForm {
	private String to;
	private String name;
	private String companyName;
}
