package com.jeremw.example.emailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Email Sender application.
 *
 * <p>
 * This is the entry point for the Spring Boot application. The {@link SpringBootApplication} annotation denotes a configuration class
 * that declares one or more {@link org.springframework.context.annotation.Bean} methods and also triggers auto-configuration and component scanning.
 * </p>
 *
 * <p>
 * The {@code main} method uses {@link SpringApplication#run(Class, String...)} to launch the application.
 * </p>
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 17/05/2024
 */
@SpringBootApplication
public class EmailSenderApplication {

	/**
	 * Main method to launch the Email Sender application.
	 *
	 * <p>This method serves as the entry point for the Java application. It delegates to Spring Boot's {@link SpringApplication#run(Class, String...)}
	 * method to start the application.</p>
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(EmailSenderApplication.class, args);
	}

}
